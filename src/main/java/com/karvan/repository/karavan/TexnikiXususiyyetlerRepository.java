package com.karvan.repository.karavan;

import com.karvan.entity.karavan.TexnikiXususiyyetler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TexnikiXususiyyetlerRepository extends JpaRepository<TexnikiXususiyyetler, Long> {
}
