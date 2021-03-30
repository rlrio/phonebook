package se.rlrio.phonebook.repository;

import se.rlrio.phonebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByFirstNameIgnoreCaseStartingWith(String name);
}
