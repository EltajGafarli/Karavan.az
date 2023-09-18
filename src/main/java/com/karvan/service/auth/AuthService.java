package com.karvan.service.auth;

import com.karvan.dto.user.LoginRequestDto;
import com.karvan.dto.user.ProfileInformationDto;
import com.karvan.dto.user.RegisterRequestDto;
import com.karvan.entity.user.Role;
import com.karvan.entity.user.User;
import com.karvan.enums.RoleEnum;
import com.karvan.mapper.UserMapper;
import com.karvan.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository;

    private final UserMapper userMapper;
    @Value("${admin.email}")
    private String adminEmail;

    @Transactional
    public String register(RegisterRequestDto dto) {
        Optional<User> usersByEmail = userRepository.findUsersByEmail(dto.getEmail());
        if(usersByEmail.isPresent()) {
            throw new IllegalStateException(dto.getEmail() + " exist");
        }

        User user = User
                .builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .locked(true)
                .enabled(true)
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();


        if(adminEmail.equals(user.getEmail())) {
            user.addRole(
                    Role
                            .builder()
                            .roleEnum(RoleEnum.ADMIN)
                            .build()
            );
        }

        user.addRole(
                Role
                        .builder()
                        .roleEnum(RoleEnum.CUSTOMER)
                        .build()

        );

        this.userRepository.save(user);

        return "Register";
    }


    public String login(LoginRequestDto dto, HttpServletRequest request, HttpServletResponse response) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail().trim(), dto.getPassword().trim())
        );


        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticate);

        securityContextRepository.saveContext(context, request, response);

        return "Login";
    }


    @Transactional
    public void updateUser(ProfileInformationDto profileInformationDto, UserDetails userDetails) {
        User user = userRepository
                .findUsersByEmail(
                        userDetails.getUsername()
                )
                .orElseThrow(
                        () -> new IllegalStateException("User not found")
                );


        if(profileInformationDto.getEmail() != null) {
            user.setEmail(profileInformationDto.getEmail());
        }

        if(profileInformationDto.getFirstName() != null) {
            user.setFirstName(profileInformationDto.getFirstName());
        }

        if(profileInformationDto.getLastName() != null) {
            user.setLastName(profileInformationDto.getLastName());
        }


        if(profileInformationDto.getPhoneNumber() != null) {
            user.setFinCode(profileInformationDto.getPhoneNumber());
        }

        if(profileInformationDto.getFinNumber() != null) {
            user.setFinCode(profileInformationDto.getFinNumber());
        }

        userRepository.save(user);


    }

    public ProfileInformationDto getUser(UserDetails userDetails){
        User user = this.userRepository.findUsersByEmail(
                        userDetails.getUsername()
                )
                .orElseThrow(
                        () -> new IllegalStateException("User not found")
                );

        return this.userMapper.userToProfileInformationDto(user);
    }

}

