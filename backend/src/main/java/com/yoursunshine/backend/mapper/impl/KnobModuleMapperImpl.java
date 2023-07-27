package com.yoursunshine.backend.mapper.impl;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.entity.DimmerModule;
import com.yoursunshine.backend.entity.KnobModule;
import com.yoursunshine.backend.entity.RgbModule;
import com.yoursunshine.backend.mapper.KnobModuleMapper;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public class KnobModuleMapperImpl implements KnobModuleMapper {

    @Override
    public List<KnobModuleDetailDto> entityListToDetailDtoList(List<KnobModule> knobModules) {
        if(knobModules == null){
            return null;
        }

        List<KnobModuleDetailDto> list = new ArrayList<KnobModuleDetailDto>( knobModules.size() );
        for ( KnobModule knobModule : knobModules ) {
            list.add( knobModuleToKnobModuleDetailDto( knobModule ) );
        }

        return list;
    }

    private KnobModuleDetailDto knobModuleToKnobModuleDetailDto(KnobModule knobModule) {
        if(knobModule == null){
            return null;
        }

        Long id = knobModule.getId();
        String title = knobModule.getTitle();
        Integer pin_number = knobModule.getPinNumber();
        Long group_id = knobModule.getGroup().getId();

        String color = null;
        Integer brightness = null;

        if(knobModule instanceof RgbModule){
            color = ((RgbModule) knobModule).getColor();
        } else {
            brightness = ((DimmerModule) knobModule).getBrightness();
        }

        return new KnobModuleDetailDto( id, title, pin_number, color, brightness, group_id );
    }
}
