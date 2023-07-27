package com.yoursunshine.backend.mapper;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleCreateDto;
import com.yoursunshine.backend.entity.RgbModule;
import org.mapstruct.Mapper;

@Mapper
public interface RgbModuleMapper {
    /**
     * Map a rgb module entity to a rgb module dto
     * @param rgbModule the rgb module to map
     * @return the mapped rgb module
     */
    RgbModule createDtoToEntity(RgbModuleCreateDto rgbModule);

    /**
     * Map a rgb module entity to a knob module dto
     * @param rgbModule the rgb module to map
     * @return the mapped rgb module
     */
    KnobModuleDetailDto entityToDetailDto(RgbModule rgbModule);
}