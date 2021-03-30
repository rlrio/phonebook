package se.rlrio.phonebook.repository;

import se.rlrio.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByUserId(int userId);

    Optional<Contact> findByIdAndUserId(int contactId, int userId);

    List<Contact> findByPhoneAndUserId(String phone, int userId);
}
