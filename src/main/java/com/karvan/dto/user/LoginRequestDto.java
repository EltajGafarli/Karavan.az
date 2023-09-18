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
@ToString
@Builder
public class LoginRequestDto {
    @Email(message = "email should valid")
    private String email;

    @NotNull(message = "field cant be null")
    @NotBlank(message = "field cant be blank")
    @NotEmpty(message = "field cant be empty")
    private String password;
}
