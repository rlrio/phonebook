package com.rlrio.phonebook.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserDto {
    private int id;
    @NotNull
    private String firstName;
    private String lastName;
}
