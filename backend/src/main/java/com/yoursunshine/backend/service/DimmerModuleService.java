package com.yoursunshine.backend.service;

import com.yoursunshine.backend.endpoint.dto.DimmerModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.DimmerModuleUpdateDto;
import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import org.springframework.stereotype.Service;

@Service
public interface DimmerModuleService {
    /**
     * Create a new dimmer module
     * @param group_id the id of the group to create the dimmer module in
     * @param dimmerModule the dimmer module to create
     * @return the created dimmer module
     */
    KnobModuleDetailDto create(Long group_id, DimmerModuleCreateDto dimmerModule);

    /**
     * Update a dimmer module
     * @param id the id of the dimmer module to update
     * @param dimmerModule the dimmer module to update
     * @return the updated dimmer module
     */
    KnobModuleDetailDto update(Long id, DimmerModuleUpdateDto dimmerModule);
}
