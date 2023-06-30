package com.yoursunshine.backend.service;

import com.yoursunshine.backend.endpoint.dto.GroupCreateDto;
import com.yoursunshine.backend.endpoint.dto.GroupDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

    /**
     * Create a new group
     * @param group the group to create
     * @return the created group
     */
    GroupDetailDto create(GroupCreateDto group);

    /**
     * Get all groups
     * @return all groups
     */
    List<GroupDetailDto> getAll();

    /**
     * Update a group
     * @param id the id of the group to update
     * @param group the group to update
     * @return the updated group
     */
    GroupDetailDto update(Long id, GroupDetailDto group);

    /**
     * Delete a group
     * @param id the id of the group to delete
     */
    void delete(Long id);
}
