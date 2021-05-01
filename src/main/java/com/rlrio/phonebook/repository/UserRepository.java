package com.rlrio.phonebook.repository;

import com.rlrio.phonebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByFirstNameIgnoreCaseStartingWith(String name);
}
