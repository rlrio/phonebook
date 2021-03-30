package se.rlrio.phonebook.dto.utils;

import se.rlrio.phonebook.dto.ContactDto;
import se.rlrio.phonebook.model.Contact;

import java.util.List;

public interface MapperUtilsContact {
    ContactDto mapToContactDto(Contact contact);

    List<ContactDto> listContactsToListDto(List<Contact> contacts);
}
