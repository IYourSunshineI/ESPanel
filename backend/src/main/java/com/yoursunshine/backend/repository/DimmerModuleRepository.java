package com.yoursunshine.backend.repository;

import com.yoursunshine.backend.entity.DimmerModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimmerModuleRepository extends JpaRepository<DimmerModule, Long> {
}
