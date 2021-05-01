package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.rlrio.phonebook.dto.UserDto;
import com.rlrio.phonebook.exception.PhonebookException;
import com.rlrio.phonebook.model.User;
import com.rlrio.phonebook.repository.UserRepository;
import com.rlrio.phonebook.service.impl.UserServiceImpl;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceTestImpl {

    private static final int USER_ONE_ID = 1;

    @Mock
    private UserRepository repoMock;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
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
        Assertions.assertNotNull(test);
        Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void findByIdAndUserIsNull() {
        Assertions.assertThrows(PhonebookException.class, () -> userService.findById(USER_ONE_ID));
        Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void createUserSuccess() {
        Mockito.when(repoMock.save(any())).thenReturn(user);
        UserDto test = userService.create(user);
        Assertions.assertNotNull(test);
        Mockito.verify(repoMock, Mockito.times(1)).save(any());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void updateUserAndUserNotExists() {
        Assertions.assertThrows(PhonebookException.class, () -> userService.update(USER_ONE_ID, user));
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
        Assertions.assertNotNull(test);
        Mockito.verify(repoMock, Mockito.times(1)).save(any());
    }


    @Test
    public void findUsersByNameAndUsersExist() {
        List<User> users = Arrays.asList(user);
        Mockito.when(repoMock.findAllByFirstNameIgnoreCaseStartingWith(ArgumentMatchers.anyString())).thenReturn(users);
        List<UserDto> userList = userService.findByName("TONY");
        Assertions.assertEquals(userList.size(), 1);
        Mockito.verify(repoMock, Mockito.times(1)).findAllByFirstNameIgnoreCaseStartingWith(ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void findAllUsers() {
        List<User> users = Arrays.asList(user);
        Mockito.when(repoMock.findAll()).thenReturn(users);
        List<UserDto> userList = userService.getAll();
        Assertions.assertEquals(userList.size(), users.size());
        Mockito.verify(repoMock, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void deleteUserSuccess() {
        Assertions.assertThrows(PhonebookException.class, () -> userService.delete(USER_ONE_ID));
    }

}