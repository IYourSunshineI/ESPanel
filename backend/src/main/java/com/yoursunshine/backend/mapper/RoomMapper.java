package com.yoursunshine.backend.mapper;

import com.yoursunshine.backend.endpoint.dto.RoomCreateDto;
import com.yoursunshine.backend.endpoint.dto.RoomDetailDto;
import com.yoursunshine.backend.entity.Room;
import org.mapstruct.Mapper;

import java.util.List;

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

    /**
     * Map a list of room entities to a list of room detail dtos
     * @param rooms the rooms to map
     * @return the mapped rooms
     */
    List<RoomDetailDto> entityListToDetailDtoList(List<Room> rooms);
}
