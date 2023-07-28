package com.yoursunshine.backend.repository;

import com.yoursunshine.backend.entity.RgbModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RgbModuleRepository extends JpaRepository<RgbModule, Long> {
}
