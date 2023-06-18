package com.yoursunshine.backend.service;

import com.yoursunshine.backend.endpoint.dto.RoomCreateDto;
import com.yoursunshine.backend.endpoint.dto.RoomDetailDto;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {

    /**
     * Create a new room
     * @param room the room to create
     * @return the created room
     */
    RoomDetailDto create(RoomCreateDto room);
}
