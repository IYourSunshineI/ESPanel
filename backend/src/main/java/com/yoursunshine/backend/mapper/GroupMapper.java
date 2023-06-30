package com.yoursunshine.backend.mapper;

import com.yoursunshine.backend.endpoint.dto.GroupCreateDto;
import com.yoursunshine.backend.endpoint.dto.GroupDetailDto;
import com.yoursunshine.backend.entity.EspGroup;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {

    /**
     * Map a group create dto to a group entity
     * @param group the group to map
     * @return the mapped group
     */
    EspGroup createDtoToEntity(GroupCreateDto group);

    /**
     * Map a group entity to a group detail dto
     * @param group the group to map
     * @return the mapped group
     */
    GroupDetailDto entityToDetailDto(EspGroup group);

    /**
     * Map a group detail dto to a group entity
     * @param group the group to map
     * @return the mapped group
     */
    EspGroup detailDtoToEntity(GroupDetailDto group);

    /**
     * Map a list of group entities to a list of group detail dtos
     * @param groups the groups to map
     * @return the mapped groups
     */
    List<GroupDetailDto> entityListToDetailDtoList(List<EspGroup> groups);
}
