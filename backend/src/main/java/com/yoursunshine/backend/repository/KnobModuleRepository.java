package com.yoursunshine.backend.repository;

import com.yoursunshine.backend.entity.KnobModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnobModuleRepository extends JpaRepository<KnobModule, Long> {

    /**
     * Get all knob modules in a group
     * @param group_id the id of the group to get the knob modules from
     * @return all knob modules
     */
    @Query("SELECT k FROM KnobModule k WHERE k.group.id = :group_id")
    List<KnobModule> getAll(@Param("group_id") Long group_id);
}
