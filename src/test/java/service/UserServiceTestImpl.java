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
import se.rlrio.phonebook.dto.UserDto;
import se.rlrio.phonebook.exception.ApiException;
import se.rlrio.phonebook.model.User;
import se.rlrio.phonebook.repository.UserRepository;
import se.rlrio.phonebook.service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTestImpl {

    private static final int USER_ONE_ID = 1;

    @Mock
    private UserRepository repoMock;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;

    @Before
    public void init() {
        user = new User();
        user.setId(USER_ONE_ID);
        user.setFirstName("Tony");
        user.setLastName("Stark");
        userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
    }

    @Test
    public void findByIdAndUserExists() {
        Mockito.when(repoMock.findById(USER_ONE_ID)).thenReturn(Optional.of(user));
        UserDto test = userService.findById(USER_ONE_ID);
        Assert.assertNotNull(test);
        Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test(expected = ApiException.class)
    public void findByIdAndUserIsNull() {
        UserDto test = userService.findById(USER_ONE_ID);
        Assert.assertNull(test);
        Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void createUserSuccess() {
        Mockito.when(repoMock.save(any())).thenReturn(user);
        UserDto test = userService.create(user);
        Assert.assertNotNull(test);
        Mockito.verify(repoMock, Mockito.times(1)).save(any());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test(expected = ApiException.class)
    public void updateUserAndUserNotExists() {
        userService.update(USER_ONE_ID, user);
        Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void updateUserSuccess() {
        User newData = new User();
        newData.setFirstName("Sam");
        newData.setLastName("Wilson");
        newData.setId(USER_ONE_ID);
        Mockito.when(repoMock.findById(any())).thenReturn(Optional.of(user));
        Mockito.when(repoMock.save(any())).thenReturn(newData);
        UserDto test = userService.update(USER_ONE_ID, newData);
        Assert.assertNotNull(test);
        Mockito.verify(repoMock, Mockito.times(1)).save(any());
    }


    @Test
    public void findUsersByNameAndUsersExist() {
        List<User> users = Arrays.asList(user);
        Mockito.when(repoMock.findAllByFirstNameIgnoreCaseStartingWith(ArgumentMatchers.anyString())).thenReturn(users);
        List<UserDto> userList = userService.findByName("TONY");
        assertEquals(userList.size(), 1);
        Mockito.verify(repoMock, Mockito.times(1)).findAllByFirstNameIgnoreCaseStartingWith(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void findAllUsers() {
        List<User> users = Arrays.asList(user);
        Mockito.when(repoMock.findAll()).thenReturn(users);
        List<UserDto> userList = userService.getAll();
        assertEquals(userList.size(), users.size());
        Mockito.verify(repoMock, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test(expected = ApiException.class)
    public void deleteUserSuccess() {
        userService.delete(USER_ONE_ID);
        userService.findById(USER_ONE_ID);
    }

}