package com.rlrio.phonebook.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
public class UserDto {
    private int id;
    @NotNull
    private String firstName;
    private String lastName;

    public UserDto() {
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
