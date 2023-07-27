package com.yoursunshine.backend.mapper;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleUpdateDto;
import com.yoursunshine.backend.entity.RgbModule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    @Mapping(expression = "java(rgbModule.getGroup().getId())", target = "group_id")
    KnobModuleDetailDto entityToDetailDto(RgbModule rgbModule);

    /**
     * Map a rgb module dto to a rgb module entity
     * @param rgbModule the rgb module to map
     * @return the mapped rgb module
     */
    RgbModule updateDtoToEntity(RgbModuleUpdateDto rgbModule);
}
