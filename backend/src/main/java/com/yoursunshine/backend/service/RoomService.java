package com.yoursunshine.backend.service;

import com.yoursunshine.backend.endpoint.dto.RoomCreateDto;
import com.yoursunshine.backend.endpoint.dto.RoomDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    /**
     * Create a new room
     * @param room the room to create
     * @return the created room
     */
    RoomDetailDto create(RoomCreateDto room);

    /**
     * Get all rooms
     * @return all rooms
     */
    List<RoomDetailDto> getAll();

    /**
     * Update a room
     * @param id the id of the room to update
     * @param room the room to update
     * @return the updated room
     */
    RoomDetailDto update(Long id, RoomDetailDto room);
}
