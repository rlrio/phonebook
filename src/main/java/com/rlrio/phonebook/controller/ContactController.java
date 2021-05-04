package com.rlrio.phonebook.controller;

import com.rlrio.phonebook.dto.ContactDto;
import com.rlrio.phonebook.model.Contact;
import com.rlrio.phonebook.service.ContactService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {
    private static final Logger log = LogManager.getLogger(ContactController.class);
    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @PostMapping({"/users/{userId}/contacts/add"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContactDto> createContact(@PathVariable("userId") int userId, @Valid @RequestBody Contact contact) {
        log.info("Contact with name:{} and phone number:{} has been created", contact.getFirstName() + " " + contact.getLastName(), contact.getPhone());
        return new ResponseEntity<>(service.create(userId, contact), HttpStatus.CREATED);
    }

    @PutMapping({"/users/{userId}/contacts/{contactId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ContactDto> updateContact(@PathVariable("userId") int userId, @PathVariable("contactId") int contactId, @Valid @RequestBody Contact contact) {
        log.info("Contact with id:{} has been changed. New contact data is:{}", contactId, contact.toString());
        return ResponseEntity.ok(service.update(userId, contactId, contact));
    }

    @DeleteMapping({"/users/{userId}/contacts/{contactId}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable("userId") int userId, @PathVariable("contactId") int contactId) {
        service.delete(userId, contactId);
        log.info("Contact with id:{} has been deleted", contactId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping({"/users/{userId}/contacts"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<HttpStatus> deleteAllContacts(@PathVariable("userId") int userId) {
        service.deleteAllContacts(userId);
        log.info("Contacts for userId:{} have been deleted", userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping({"/users/{userId}/contacts"})
    public ResponseEntity<List<ContactDto>> getAllContacts(@PathVariable("userId") int userId) {
        log.info("Contacts of userId:{} were found", userId);
        return ResponseEntity.ok(service.getAll(userId));
    }

    @GetMapping({"/users/{userId}/contacts/{contactId}"})
    public ResponseEntity<ContactDto> getContactById(@PathVariable("userId") int userId, @PathVariable("contactId") int contactId) {
        log.info("Contact with id:{} of userId:{} was found", contactId, userId);
        return ResponseEntity.ok(service.findById(contactId, userId));
    }

    @GetMapping({"users/{userId}/contacts/search"})
    public ResponseEntity<List<ContactDto>> getContactsByPhone(@PathVariable("userId") int userId, @RequestParam String phone) {
        log.info("Contacts of userId:{} with phone:{} were found", userId, phone);
        return ResponseEntity.ok(service.findByPhone(phone, userId));
    }

}
