package se.rlrio.phonebook.dto.utils;

import se.rlrio.phonebook.dto.UserDto;
import se.rlrio.phonebook.model.User;

import java.util.List;

public interface MapperUtilsUser {
    UserDto mapUserToDto(User user);

    List<UserDto> listUsersToListDto(List<User> users);
}
