package com.rlrio.phonebook.dto.mappers;

import com.rlrio.phonebook.dto.ContactDto;
import com.rlrio.phonebook.exception.ErrorType;
import com.rlrio.phonebook.exception.PhonebookException;
import com.rlrio.phonebook.model.Contact;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class ContactMapper {
    public ContactDto mapToContactDto(Contact contact) {
        if (contact == null) {
            throw new PhonebookException(ErrorType.CONTACT_NOT_FOUND);
        }
        return ContactDto.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .phone(contact.getPhone())
                .build();
    }

    public List<ContactDto> listContactsToListDto(List<Contact> contacts) {
        if (contacts == null) {
            throw new PhonebookException(ErrorType.CONTACT_NOT_FOUND);
        }
        return contacts.stream()
                .map(this::mapToContactDto)
                .collect(Collectors.toList());
    }
}
