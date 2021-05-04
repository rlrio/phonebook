package com.rlrio.phonebook.dto.mappers;

import com.rlrio.phonebook.dto.UserDto;
import com.rlrio.phonebook.exception.ErrorType;
import com.rlrio.phonebook.exception.PhonebookException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import com.rlrio.phonebook.model.User;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class UserMapper {
    public UserDto mapUserToDto(User user) {
        if (user == null) {
            throw new PhonebookException(ErrorType.USER_NOT_FOUND);
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public List<UserDto> listUsersToListDto(List<User> users) {
        if (users == null) {
            throw new PhonebookException(ErrorType.USER_NOT_FOUND);
        }
        return users.stream()
                .map(this::mapUserToDto)
                .collect(Collectors.toList());
    }
}
