package com.karvan.mapper;

import com.karvan.dto.user.LoginRequestDto;
import com.karvan.dto.user.ProfileInformationDto;
import com.karvan.dto.user.RegisterRequestDto;
import com.karvan.dto.user.UserDto;
import com.karvan.entity.user.User;
import com.karvan.repository.user.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {

    @Autowired
    private UserRepository userRepository;

    abstract User loginToUser(LoginRequestDto dto);
    abstract User registerToUser(RegisterRequestDto dto);
    abstract User userDtoToUser(UserDto dto);
    abstract UserDto userToUserDto(User user);

    public ProfileInformationDto userToProfileInformationDto(User user) {
        return ProfileInformationDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(null)
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .finNumber(user.getFinCode())
                .build();
    }


}
