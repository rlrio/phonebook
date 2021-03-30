package se.rlrio.phonebook.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    private int id;
    @NotNull
    private String firstName;
    private String lastName;

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserDTO(" +
                "id=" + this.getId() +
                ", firstName=" + this.getFirstName() +
                ", lastName=" + this.getLastName() +
                ')';
    }
}
