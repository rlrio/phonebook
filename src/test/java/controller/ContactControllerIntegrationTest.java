package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import se.rlrio.phonebook.Application;
import se.rlrio.phonebook.controller.ContactController;
import se.rlrio.phonebook.model.Contact;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ContactControllerIntegrationTest {
    @Autowired
    private ContactController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private String json;

    @Before
    public void setup() throws JsonProcessingException {
        Contact contact = new Contact();
        contact.setFirstName("Bruce");
        contact.setLastName("Banner");
        contact.setPhone("+18881567689");
        json = mapper.writeValueAsString(contact);
    }

    @Test
    public void createContactSuccess() throws Exception {
        this.mockMvc.perform(post("/users/1/contacts/add")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void updateContactSuccess() throws Exception {
        this.mockMvc.perform(put("/users/1/contacts/1")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getContactByIdSuccess() throws Exception {
        this.mockMvc.perform(get("/users/1/contacts/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getAllContactsOfUserSuccess() throws Exception {
        this.mockMvc.perform(get("/users/1/contacts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    public void getContactByPhoneSuccess() throws Exception {
        this.mockMvc.perform(get("/users/1/contacts/search/?phone=18881216513")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }

    @Test
    public void deleteContactSuccess() throws Exception {
        this.mockMvc.perform(delete("/users/1/contacts/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteAllContactsSuccess() throws Exception {
        this.mockMvc.perform(delete("/users/2/contacts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
