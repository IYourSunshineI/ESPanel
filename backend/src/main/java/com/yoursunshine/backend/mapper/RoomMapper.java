package com.yoursunshine.backend.mapper;

import com.yoursunshine.backend.endpoint.dto.RoomCreateDto;
import com.yoursunshine.backend.endpoint.dto.RoomDetailDto;
import com.yoursunshine.backend.entity.Room;
import org.mapstruct.Mapper;

@Mapper
public interface RoomMapper {

    /**
     * Map a room create dto to a room entity
     * @param room the room to map
     * @return the mapped room
     */
    Room createDtoToEntity(RoomCreateDto room);

    /**
     * Map a room entity to a room detail dto
     * @param room the room to map
     * @return the mapped room
     */
    RoomDetailDto entityToDetailDto(Room room);
}
