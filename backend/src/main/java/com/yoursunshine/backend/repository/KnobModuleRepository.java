package com.yoursunshine.backend.repository;

import com.yoursunshine.backend.entity.KnobModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnobModuleRepository extends JpaRepository<KnobModule, Long> {

}
