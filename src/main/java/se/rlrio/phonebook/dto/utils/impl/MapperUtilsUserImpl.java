package se.rlrio.phonebook.dto.utils.impl;

import org.springframework.stereotype.Service;
import se.rlrio.phonebook.dto.UserDto;
import se.rlrio.phonebook.dto.utils.MapperUtilsUser;
import se.rlrio.phonebook.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperUtilsUserImpl implements MapperUtilsUser {

    @Override
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

    @Override
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
