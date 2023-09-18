package com.karvan.controller.auth;

import com.karvan.dto.user.LoginRequestDto;
import com.karvan.dto.user.ProfileInformationDto;
import com.karvan.dto.user.RegisterRequestDto;
import com.karvan.entity.user.SupplierRequestEntity;
import com.karvan.entity.user.User;
import com.karvan.mapper.UserMapper;
import com.karvan.model.UserDetailsImpl;
import com.karvan.service.auth.AuthService;
import com.karvan.service.auth.SupplierRequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final SupplierRequestService supplierRequestService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(dto));
    }

    @PostMapping(path = {"/login", "/login/"})
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto, HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.login(dto, request, response));
    }

    @GetMapping(path = "/user")
    @PreAuthorize(value = "hasAnyRole('CUSTOMER', 'SUPPLIER')")
    public ResponseEntity<ProfileInformationDto> getUser(@AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.authService.getUser(userDetails));
    }

    @PostMapping(path = "/update")
    public ResponseEntity<String> updateUser(@RequestBody ProfileInformationDto profileInformationDto, @AuthenticationPrincipal UserDetails userDetails) {
        this.authService.updateUser(profileInformationDto, userDetails);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Updated successfully");
    }

    @PostMapping("/be-supplier")
    public ResponseEntity<String> sendBeSupplierNotification(@AuthenticationPrincipal UserDetails userDetails) {

        this.supplierRequestService.saveRequest(userDetails);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Be Supplier Request sending successfully");
    }

}
