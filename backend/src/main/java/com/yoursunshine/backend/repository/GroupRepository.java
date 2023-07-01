package com.yoursunshine.backend.repository;

import com.yoursunshine.backend.entity.EspGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<EspGroup, Long> {

    /**
     * Get all groups in a room
     * @param room_id the id of the room to get the groups from
     * @return all groups
     */
    @Query("SELECT g FROM EspGroup g WHERE g.room.id = :room_id")
    List<EspGroup> getAll(@Param("room_id") Long room_id);
}
