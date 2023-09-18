package com.karvan.dto.user;

import jakarta.validation.constraints.Email;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ProfileInformationDto {

    private String firstName;
    private String lastName;

    private String phoneNumber;

    @Email(message = "email must be valid")
    private String email;

    private String finNumber;
    private String password;

}
