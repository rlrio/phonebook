package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import se.rlrio.phonebook.dto.ContactDto;
import se.rlrio.phonebook.exception.ApiException;
import se.rlrio.phonebook.model.Contact;
import se.rlrio.phonebook.model.User;
import se.rlrio.phonebook.repository.ContactRepository;
import se.rlrio.phonebook.repository.UserRepository;
import se.rlrio.phonebook.service.impl.ContactServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTestImpl {
    private static final int CONTACT_ONE_ID = 1;
    private static final int USER_ONE_ID = 1;

    @Mock
    private UserRepository userRepoMock;


    @Mock
    private ContactRepository contactRepoMock;

    @InjectMocks
    private ContactServiceImpl contactService;

    private Contact contact;
    private User user;
    private ContactDto contactDto;

    @Before
    public void init() {
        user = new User();
        user.setId(USER_ONE_ID);
        user.setFirstName("Peter");
        user.setLastName("Parker");
        contact = new Contact();
        contact.setId(CONTACT_ONE_ID);
        contact.setFirstName("Tony");
        contact.setLastName("Stark");
        contact.setPhone("888112233");
        contact.setUser(user);
        contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setPhone(contact.getPhone());
    }

    @Test
    public void findByIdAndContactExists() {
        Mockito.when(userRepoMock.existsById(USER_ONE_ID)).thenReturn(true);
        Mockito.when(contactRepoMock.findByIdAndUserId(CONTACT_ONE_ID, USER_ONE_ID)).thenReturn(Optional.of(contact));
        ContactDto test = contactService.findById(CONTACT_ONE_ID, USER_ONE_ID);
        Assert.assertNotNull(test);
        Mockito.verify(contactRepoMock, Mockito.times(1))
                .findByIdAndUserId(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(contactRepoMock);
    }

    @Test(expected = ApiException.class)
    public void findByIdAndUserIsNull() {
        ContactDto test = contactService.findById(CONTACT_ONE_ID, USER_ONE_ID);
        Assert.assertNull(test);
        Mockito.verify(contactRepoMock, Mockito.times(1))
                .findByIdAndUserId(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(contactRepoMock);
    }


    @Test(expected = ApiException.class)
    public void createContactAndGetException() {
        ContactDto test = contactService.create(USER_ONE_ID, contact);
        Assert.assertNull(test);
        Mockito.verify(contactRepoMock, Mockito.times(1))
                .findByIdAndUserId(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(contactRepoMock);
    }

    @Test
    public void createContactSuccess() {
        Mockito.when(userRepoMock.findById(USER_ONE_ID)).thenReturn(Optional.of(user));
        Mockito.when(contactRepoMock.save(any())).thenReturn(contact);
        ContactDto test = contactService.create(USER_ONE_ID, contact);
        Assert.assertNotNull(test);
        Mockito.verify(contactRepoMock, Mockito.times(1)).save(any());
        Mockito.verifyNoMoreInteractions(contactRepoMock);
    }


    @Test(expected = ApiException.class)
    public void updateContactAndContactNotExists() {
        Mockito.when(userRepoMock.existsById(USER_ONE_ID)).thenReturn(true);
        contactService.update(USER_ONE_ID, CONTACT_ONE_ID, contact);
        Mockito.verify(contactRepoMock, Mockito.times(1))
                .findByIdAndUserId(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(contactRepoMock);
    }

    @Test
    public void updateContactSuccess() {
        Contact newData = new Contact();
        newData.setFirstName("Bruce");
        newData.setLastName("Banner");
        newData.setPhone("+18881245674");
        newData.setId(CONTACT_ONE_ID);
        newData.setUser(user);
        Mockito.when(userRepoMock.existsById(USER_ONE_ID)).thenReturn(true);
        Mockito.when(contactRepoMock.findById(any())).thenReturn(Optional.of(contact));
        Mockito.when(contactRepoMock.save(any())).thenReturn(newData);
        ContactDto test = contactService.update(USER_ONE_ID, CONTACT_ONE_ID, newData);
        Assert.assertNotNull(test);
        Mockito.verify(contactRepoMock, Mockito.times(1)).save(any());
    }


    @Test
    public void findUsersByPhoneAndContactExist() {
        Mockito.when(userRepoMock.existsById(USER_ONE_ID)).thenReturn(true);
        List<Contact> contacts = Arrays.asList(contact);
        Mockito.when(contactRepoMock
                .findByPhoneAndUserId(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).thenReturn(contacts);
        List<ContactDto> contactList = contactService.findByPhone("888112233", USER_ONE_ID);
        assertEquals(contactList.size(), 1);
        Mockito.verify(contactRepoMock, Mockito.times(1))
                .findByPhoneAndUserId(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(contactRepoMock);
    }

    @Test
    public void findAllContacts() {
        List<Contact> contacts = Arrays.asList(contact);
        Mockito.when(userRepoMock.existsById(USER_ONE_ID)).thenReturn(true);
        Mockito.when(contactRepoMock.findByUserId(USER_ONE_ID)).thenReturn(contacts);
        List<ContactDto> contactList = contactService.getAll(USER_ONE_ID);
        assertEquals(contactList.size(), 1);
        Mockito.verify(contactRepoMock, Mockito.times(1)).findByUserId(ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(contactRepoMock);
    }

    @Test(expected = ApiException.class)
    public void deleteUserSuccess() {
        contactService.delete(CONTACT_ONE_ID, USER_ONE_ID);
        contactService.findById(CONTACT_ONE_ID, USER_ONE_ID);
    }

}
