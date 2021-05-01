package com.rlrio.phonebook.dto.mappers;

import com.rlrio.phonebook.dto.UserDto;
import org.springframework.stereotype.Service;
import com.rlrio.phonebook.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {
    public UserMapper() {
    }
    public UserDto mapUserToDto(User user) {
        if (user == null) {
            return null;
        } else {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            return userDto;
        }
    }

    public List<UserDto> listUsersToListDto(List<User> users) {
        if (users == null) {
            return null;
        } else {
            List<UserDto> list = new ArrayList<>(users.size());
            for (User user : users) {
                list.add(this.mapUserToDto(user));
            }
            return list;
        }
    }
}
