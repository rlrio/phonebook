package se.rlrio.phonebook.controller;

import se.rlrio.phonebook.dto.ContactDto;
import se.rlrio.phonebook.service.ContactService;
import se.rlrio.phonebook.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {
    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @PostMapping({"/users/{userId}/contacts/add"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContactDto> createContact(@PathVariable("userId") int userId, @Valid @RequestBody Contact contact) {
        return new ResponseEntity<>(this.service.create(userId, contact), HttpStatus.CREATED);
    }

    @PutMapping({"/users/{userId}/contacts/{contactId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ContactDto> updateContact(@PathVariable("userId") int userId, @PathVariable("contactId") int contactId, @Valid @RequestBody Contact contact) {
        return ResponseEntity.ok(this.service.update(userId, contactId, contact));
    }

    @DeleteMapping({"/users/{userId}/contacts/{contactId}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable("userId") int userId, @PathVariable("contactId") int contactId) {
        this.service.delete(userId, contactId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping({"/users/{userId}/contacts"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<HttpStatus> deleteAllContacts(@PathVariable("userId") int userId) {
        this.service.deleteAllContacts(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping({"/users/{userId}/contacts"})
    public ResponseEntity<List<ContactDto>> getAllContacts(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(this.service.getAll(userId));
    }

    @GetMapping({"/users/{userId}/contacts/{contactId}"})
    public ResponseEntity<ContactDto> getContactById(@PathVariable("userId") int userId, @PathVariable("contactId") int contactId) {
        return ResponseEntity.ok(this.service.findById(contactId, userId));
    }

    @GetMapping({"users/{userId}/contacts/search"})
    public ResponseEntity<List<ContactDto>> getContactsByPhone(@PathVariable("userId") int userId, @RequestParam String phone) {
        return ResponseEntity.ok(this.service.findByPhone(phone, userId));
    }

}
