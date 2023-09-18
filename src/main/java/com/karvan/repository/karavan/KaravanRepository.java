package com.karvan.repository.karavan;

import com.karvan.entity.karavan.Karavan;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KaravanRepository extends JpaRepository<Karavan, Long>, JpaSpecificationExecutor<Karavan> {
    List<Karavan> findAllOrderByCreatedAtDesc();
}
