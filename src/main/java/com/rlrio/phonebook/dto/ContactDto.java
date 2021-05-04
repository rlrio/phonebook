package com.rlrio.phonebook.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ContactDto {
    private Integer id;
    private String firstName;
    private String lastName;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d{11}")
    private String phone;
}
