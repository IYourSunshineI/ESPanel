package com.yoursunshine.backend.service;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleUpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface RgbModuleService {
    /**
     * Create a new rgb module
     * @param group_id the id of the group to create the rgb module in
     * @param rgbModule the rgb module to create
     * @return the created rgb module
     */
    KnobModuleDetailDto create(Long group_id, RgbModuleCreateDto rgbModule);

    /**
     * Update a rgb module
     * @param id the id of the rgb module to update
     * @param rgbModule the rgb module to update
     * @return the updated rgb module
     */
    KnobModuleDetailDto update(Long id, RgbModuleUpdateDto rgbModule);
}
