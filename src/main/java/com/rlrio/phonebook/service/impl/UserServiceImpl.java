package com.rlrio.phonebook.service.impl;

import com.rlrio.phonebook.dto.UserDto;
import com.rlrio.phonebook.service.UserService;
import com.rlrio.phonebook.dto.mappers.UserMapper;
import com.rlrio.phonebook.exception.PhonebookException;
import com.rlrio.phonebook.exception.ErrorType;
import com.rlrio.phonebook.model.Contact;
import com.rlrio.phonebook.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.rlrio.phonebook.repository.ContactRepository;
import com.rlrio.phonebook.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@EnableTransactionManagement
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final UserMapper mapper = new UserMapper();

    public UserDto create(User user) throws PhonebookException {
        log.info("User with this firstName:{} and lastName:{} has been created", user.getFirstName(), user.getLastName());
        return mapper.mapUserToDto(userRepository.save(user));
    }

    public UserDto update(int id, User user) {
        User updated = userRepository
                .findById(id)
                .map(this::updateUser)
                .orElseThrow(() -> new PhonebookException(ErrorType.USER_NOT_FOUND)
        );
        log.info("User with this id:{} has been changed. New user data is:{}", id, user.toString());
        return mapper.mapUserToDto(updated);
    }

    private User updateUser(User user) {
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public void delete(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            List<Contact> contacts = contactRepository.findByUserId(user.get().getId());
            if (!contacts.isEmpty()) {
                for (Contact contact: contacts) {
                    log.info("Contact with id:{} has been deleted", contact.getId());
                    contactRepository.delete(contact);
                }
            }
            log.info("User with this id:{} has been deleted", id);
            userRepository.delete(user.get());
        } else {
            throw new PhonebookException(ErrorType.USER_NOT_FOUND);
        }
        this.userRepository.deleteById(id);
    }

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new PhonebookException(ErrorType.USER_NOT_FOUND);
        } else {
            log.info("Users:{} were found", users);
            return mapper.listUsersToListDto(users);
        }

    }

    public List<UserDto> findByName(String name) {
        List<User> users = userRepository.findAllByFirstNameIgnoreCaseStartingWith(name);
        if (users.isEmpty()) {
            throw new PhonebookException(ErrorType.USER_NOT_FOUND);
        }
        log.info("Users with name:{} were found:{}", name, users);
        return mapper.listUsersToListDto(users);
    }

    public UserDto findById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new PhonebookException(ErrorType.USER_NOT_FOUND));
        log.info("User with this id:{} was found", user.getId());
        return mapper.mapUserToDto(user);
    }
}
