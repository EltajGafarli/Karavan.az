package com.karvan.dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
//    private String password;
    private String phoneNumber;
//    private boolean enabled;
//    private boolean locked;
//    private boolean accountNonExpired;
//    private boolean credentialsNonExpired;
}
