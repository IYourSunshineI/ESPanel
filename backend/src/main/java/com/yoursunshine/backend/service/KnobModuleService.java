package com.yoursunshine.backend.service;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KnobModuleService {

    /**
     * Get all knob modules from a group
     * @param group_id the id of the group
     * @return all knob modules
     */
    List<KnobModuleDetailDto> getAll(Long group_id);

    /**
     * Delete a knob module
     * @param id the id of the knob module to delete
     */
    void delete(Long id);
}
