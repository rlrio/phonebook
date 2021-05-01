package com.rlrio.phonebook.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@EqualsAndHashCode
public class ContactDto {
    private Integer id;
    private String firstName;
    private String lastName;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d{11}")
    private String phone;

    public ContactDto() {
    }

    @Override
    public String toString() {
        return "ContactDTO(id=" + this.getId() +
                ", firstName=" + this.getFirstName() +
                ", lastName=" + this.getLastName() +
                ", phone=" + this.getPhone();
    }
}
