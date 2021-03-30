package se.rlrio.phonebook.dto.utils.impl;

import se.rlrio.phonebook.dto.ContactDto;
import se.rlrio.phonebook.dto.utils.MapperUtilsContact;
import se.rlrio.phonebook.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MapperUtilsContactImpl implements MapperUtilsContact {

    @Override
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

    @Override
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
