package com.rlrio.phonebook.dto.mappers;

import com.rlrio.phonebook.dto.ContactDto;
import com.rlrio.phonebook.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactMapper {
    public ContactMapper() {
    }

    public ContactDto mapToContactDto(Contact contact) {
        if (contact == null) {
            return null;
        } else {
            ContactDto dto = new ContactDto();
            dto.setId(contact.getId());
            dto.setFirstName(contact.getFirstName());
            dto.setLastName(contact.getLastName());
            dto.setPhone(contact.getPhone());
            return dto;
        }
    }

    public List<ContactDto> listContactsToListDto(List<Contact> contacts) {
        if (contacts == null) {
            return null;
        } else {
            List<ContactDto> list = new ArrayList<>(contacts.size());
            for (Contact contact : contacts) {
                list.add(this.mapToContactDto(contact));
            }
            return list;
        }
    }
}
