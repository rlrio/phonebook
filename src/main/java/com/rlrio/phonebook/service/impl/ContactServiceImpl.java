package com.rlrio.phonebook.service.impl;

import com.rlrio.phonebook.dto.ContactDto;
import com.rlrio.phonebook.dto.mappers.ContactMapper;
import com.rlrio.phonebook.model.Contact;
import com.rlrio.phonebook.repository.ContactRepository;
import com.rlrio.phonebook.repository.UserRepository;
import com.rlrio.phonebook.service.ContactService;
import com.rlrio.phonebook.exception.PhonebookException;
import com.rlrio.phonebook.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@RequiredArgsConstructor
@Service
@EnableTransactionManagement
public class ContactServiceImpl implements ContactService {
    private static final Logger log = LogManager.getLogger(ContactServiceImpl.class);
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final ContactMapper mapper = new ContactMapper();

    public ContactDto create(int userId, Contact contact) {
        Contact saveContact = userRepository.findById(userId).map((user) -> {
            contact.setUser(user);
            log.info("Contact with name:{} and phone number:{} has been created", contact.getFirstName() + " " + contact.getLastName(), contact.getPhone());
            return contactRepository.save(contact);
        }).orElseThrow(() -> new PhonebookException(ErrorType.USER_NOT_FOUND));
        return mapper.mapToContactDto(saveContact);
    }

    public ContactDto update(int userId, int contactId, Contact contact) {
        if (!userRepository.existsById(userId)) {
            throw new PhonebookException(ErrorType.USER_NOT_FOUND);
        } else {
            Contact saveContact = contactRepository.findById(contactId).map((value) -> {
                if (value.equals(contact)) {
                    return value;
                } else {
                    value.setPhone(contact.getPhone());
                    value.setFirstName(contact.getFirstName());
                    value.setLastName(contact.getLastName());
                    log.info("Contact with this id:{} has been changed. New contact data is:{}", contactId, value.toString());
                    return contactRepository.save(value);
                }
            }).orElseThrow(() -> new PhonebookException(ErrorType.CONTACT_NOT_FOUND));
            return mapper.mapToContactDto(saveContact);
        }

    }

    public void delete(int userId, int contactId) {
        if (userRepository.existsById(userId)) {
            Contact contact = contactRepository.findByIdAndUserId(contactId, userId)
                    .orElseThrow(() -> new PhonebookException(ErrorType.USER_NOT_FOUND));
            log.info("Contact with id:{} has been deleted", contactId);
            contactRepository.delete(contact);
        } else throw new PhonebookException(ErrorType.USER_NOT_FOUND);
    }

    public void deleteAllContacts(int userId) {
        if (userRepository.existsById(userId)) {
            List<Contact> contacts = contactRepository.findByUserId(userId);
            if (!contacts.isEmpty()) {
                for (Contact contact : contacts) {
                    log.info("Contact with id:{} has been deleted", contact.getId());
                    contactRepository.delete(contact);
                }
            }
        } else throw new PhonebookException(ErrorType.USER_NOT_FOUND);
    }

    public List<ContactDto> getAll(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new PhonebookException(ErrorType.USER_NOT_FOUND);
        } else {
            List<Contact> contacts = contactRepository.findByUserId(userId);
            if (contacts.isEmpty()) {
                throw new PhonebookException(ErrorType.CONTACT_NOT_FOUND);
            } else {
                log.info("Contacts:{} of userId:{} were found", contacts, userId);
                return mapper.listContactsToListDto(contacts);
            }
        }
    }

    public List<ContactDto> findByPhone(String numberPhone, int userId) {
        if (!userRepository.existsById(userId)) {
            throw new PhonebookException(ErrorType.USER_NOT_FOUND);
        } else {
            List<Contact> contacts = contactRepository.findByPhoneAndUserId(numberPhone, userId);
            if (contacts.isEmpty()) {
                throw new PhonebookException(ErrorType.CONTACT_NOT_FOUND);
            } else {
                log.info("Contacts:{} of userId:{} with phone:{} were found", contacts, userId, numberPhone);
                return mapper.listContactsToListDto(contacts);
            }
        }
    }

    public ContactDto findById(int contactId, int userId) {
        if (!userRepository.existsById(userId)) {
            throw new PhonebookException(ErrorType.USER_NOT_FOUND);
        } else {
            ContactDto contactDto = mapper.mapToContactDto(contactRepository.findByIdAndUserId(contactId, userId)
                    .orElseThrow(() -> new PhonebookException(ErrorType.CONTACT_NOT_FOUND)));
            log.info("Contact with id:{} of userId:{} was found, contact:{}", contactId, userId, contactDto);
            return contactDto;
        }

    }
}
