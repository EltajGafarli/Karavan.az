package com.karvan.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequestDto {

    @NotNull(message = "field cant be null")
    @NotBlank(message = "field cant be blank")
    @NotEmpty(message = "field cant be empty")
    private String firstName;

    @NotNull(message = "field cant be null")
    @NotBlank(message = "field cant be blank")
    @NotEmpty(message = "field cant be empty")
    private String lastName;

    @Email(message = "email should be valid")
    private String email;

    @NotNull(message = "field cant be null")
    @NotBlank(message = "field cant be blank")
    @NotEmpty(message = "field cant be empty")
    private String password;

    @NotNull(message = "field cant be null")
    @NotBlank(message = "field cant be blank")
    @NotEmpty(message = "field cant be empty")
    private String phoneNumber;
}
