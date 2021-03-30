package se.rlrio.phonebook.service;

import se.rlrio.phonebook.dto.UserDto;
import se.rlrio.phonebook.model.User;
import java.util.List;

public interface UserService {

    UserDto create(User user);

    UserDto update(int id, User user);

    void delete(int id);

    List<UserDto> getAll();

    List<UserDto> findByName(String name);

    UserDto findById(int id);
}
