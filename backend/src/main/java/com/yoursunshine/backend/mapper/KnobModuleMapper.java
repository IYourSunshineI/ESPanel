package com.yoursunshine.backend.mapper;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.entity.KnobModule;


import java.util.List;

public interface KnobModuleMapper {

    /**
     * Map a list of knob module entitys to a knob module detail dto list
     * @param knobModules the knob modules to map
     * @return the mapped knob module
     */
    List<KnobModuleDetailDto> entityListToDetailDtoList(List<KnobModule> knobModules);
}
