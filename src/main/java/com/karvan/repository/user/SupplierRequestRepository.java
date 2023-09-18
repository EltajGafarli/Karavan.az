package com.karvan.repository.user;

import com.karvan.entity.user.SupplierRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRequestRepository extends JpaRepository<SupplierRequestEntity, Long> {
}
