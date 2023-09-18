package com.karvan.service.auth;

import com.karvan.dto.user.ProfileInformationDto;
import com.karvan.entity.user.SupplierRequestEntity;
import com.karvan.entity.user.User;
import com.karvan.mapper.UserMapper;
import com.karvan.repository.user.SupplierRequestRepository;
import com.karvan.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierRequestService {
    private final SupplierRequestRepository supplierRequestRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public void saveRequest(UserDetails userDetails) {

        SupplierRequestEntity supplierRequestEntity = new SupplierRequestEntity();
        User user = this.userRepository.findUsersByEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        supplierRequestEntity.setUser(user);
        this.supplierRequestRepository.save(supplierRequestEntity);

    }

    public List<ProfileInformationDto> getUsersWhoRequestForSupplier() {
        List<SupplierRequestEntity> allRequest = this.supplierRequestRepository.findAll();
        return allRequest
                .stream()
                .map(SupplierRequestEntity::getUser)
                .map(userMapper::userToProfileInformationDto)
                .toList();
    }




}
