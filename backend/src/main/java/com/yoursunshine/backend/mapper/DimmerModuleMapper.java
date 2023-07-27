package com.yoursunshine.backend.mapper;

import com.yoursunshine.backend.endpoint.dto.DimmerModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.DimmerModuleUpdateDto;
import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.entity.DimmerModule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DimmerModuleMapper {

    /**
     * Map a dimmer module entity to a dimmer module dto
     * @param dimmerModule the dimmer module to map
     * @return the mapped dimmer module
     */
    DimmerModule createDtoToEntity(DimmerModuleCreateDto dimmerModule);

    /**
     * Map a dimmer module entity to a knob module dto
     * @param dimmerModule the dimmer module to map
     * @return the mapped dimmer module
     */
    @Mapping(expression = "java(dimmerModule.getGroup().getId())", target = "group_id")
    KnobModuleDetailDto entityToDetailDto(DimmerModule dimmerModule);

    /**
     * Map a dimmer module dto to a dimmer module entity
     * @param dimmerModule the dimmer module to map
     * @return the mapped dimmer module
     */
    DimmerModule updateDtoToEntity(DimmerModuleUpdateDto dimmerModule);
}
