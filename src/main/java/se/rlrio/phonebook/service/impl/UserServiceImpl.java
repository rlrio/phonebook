package se.rlrio.phonebook.service.impl;

import se.rlrio.phonebook.dto.UserDto;
import se.rlrio.phonebook.dto.utils.MapperUtilsUser;
import se.rlrio.phonebook.dto.utils.impl.MapperUtilsUserImpl;
import se.rlrio.phonebook.exception.ApiException;
import se.rlrio.phonebook.exception.ErrorType;
import se.rlrio.phonebook.model.Contact;
import se.rlrio.phonebook.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import se.rlrio.phonebook.repository.ContactRepository;
import se.rlrio.phonebook.repository.UserRepository;
import se.rlrio.phonebook.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final MapperUtilsUser mapper = new MapperUtilsUserImpl();

    public UserServiceImpl(UserRepository userRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    public UserDto create(User user) throws ApiException {
        log.info("User with this firstName:{} and lastName:{} has been created", user.getFirstName(), user.getLastName());
        return this.mapper.mapUserToDto(this.userRepository.save(user));
    }

    public UserDto update(int id, User user) {
        User updated = this.userRepository.findById(id).map((user2) -> {
            if (user2.equals(user)) {
                return user2;
            } else {
                user2.setFirstName(user.getFirstName());
                user2.setLastName(user.getLastName());
                log.info("User with this id:{} has been changed. New user data is:{}", id, user2.toString());
                return this.userRepository.save(user2);
            }
        }).orElseThrow(() ->
                new ApiException(ErrorType.USER_NOT_FOUND)
        );
        return this.mapper.mapUserToDto(updated);
    }

    public void delete(int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            List<Contact> contacts = this.contactRepository.findByUserId(user.get().getId());
            if (!contacts.isEmpty()) {
                for (Contact contact: contacts) {
                    log.info("Contact with id:{} has been deleted", contact.getId());
                    this.contactRepository.delete(contact);
                }
            }
            log.info("User with this id:{} has been deleted", id);
            this.userRepository.delete(user.get());
        } else {
            throw new ApiException(ErrorType.USER_NOT_FOUND);
        }
        this.userRepository.deleteById(id);
    }

    public List<UserDto> getAll() {
        List<User> users = this.userRepository.findAll();
        if (users.isEmpty()) {
            throw new ApiException(ErrorType.USER_NOT_FOUND);
        } else {
            log.info("Users:{} were found", users);
            return this.mapper.listUsersToListDto(users);
        }

    }

    public List<UserDto> findByName(String name) {
        List<User> users = this.userRepository.findAllByFirstNameIgnoreCaseStartingWith(name);
        if (users.isEmpty()) {
            throw new ApiException(ErrorType.USER_NOT_FOUND);
        }
        log.info("Users with name:{} were found:{}", name, users);
        return this.mapper.listUsersToListDto(users);
    }

    public UserDto findById(int id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorType.USER_NOT_FOUND));
        log.info("User with this id:{} was found", user.getId());
        return this.mapper.mapUserToDto(user);
    }
}
