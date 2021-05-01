package com.rlrio.phonebook.service;

import com.rlrio.phonebook.dto.UserDto;
import com.rlrio.phonebook.model.User;
import java.util.List;

public interface UserService {

    UserDto create(User user);

    UserDto update(int id, User user);

    void delete(int id);

    List<UserDto> getAll();

    List<UserDto> findByName(String name);

    UserDto findById(int id);
}
