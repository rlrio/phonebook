package se.rlrio.phonebook.service;

import se.rlrio.phonebook.dto.ContactDto;
import se.rlrio.phonebook.model.Contact;

import java.util.List;

public interface ContactService {
    ContactDto create(int userId, Contact contact);

    ContactDto update(int userId, int contactId, Contact contact);

    void delete(int userId, int contactId);

    void deleteAllContacts(int userId);

    List<ContactDto> getAll(int userId);

    List<ContactDto> findByPhone(String phone, int userId);

    ContactDto findById(int contactId, int userId);



}
