package mapper;

import com.rlrio.phonebook.dto.ContactDto;
import com.rlrio.phonebook.dto.mappers.ContactMapper;
import com.rlrio.phonebook.model.Contact;
import com.rlrio.phonebook.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class ContactMapperTest {
    ContactMapper mapper;
    List<Contact> contacts;
    List<ContactDto> contactsDto;

    @BeforeEach
    public void init() {
        mapper = new ContactMapper();
        contacts = new ArrayList<>();
        contactsDto = new ArrayList<>();
        Contact contact = new Contact(1, "Tony", "Stark", "+18241451570", new User("Natasha", "Romanoff"));
        contacts.add(contact);
        ContactDto dto = new ContactDto();
        dto.setId(1);
        dto.setFirstName("Tony");
        dto.setLastName("Stark");
        dto.setPhone("+18241451570");
        contactsDto.add(dto);
    }

    @Test
    public void mapToContactDtoSuccess() {
        Assertions.assertEquals(contactsDto.get(0), mapper.mapToContactDto(contacts.get(0)));
    }

    @Test
    public void listContactsToListDtoSuccess() {
        Assertions.assertEquals(contactsDto, mapper.listContactsToListDto(contacts));
    }

}