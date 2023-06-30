package com.yoursunshine.backend.endpoint;

import com.yoursunshine.backend.endpoint.dto.RoomCreateDto;
import com.yoursunshine.backend.endpoint.dto.RoomDetailDto;
import com.yoursunshine.backend.service.RoomService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(value = RoomEndpoint.BASE_PATH)
public class RoomEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    static final String BASE_PATH = "/rooms";
    private final RoomService service;
    public RoomEndpoint(RoomService roomService) {
        this.service = roomService;
    }

    /**
     * Create a new room
     * @param room the room to create
     * @return the created room
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDetailDto create(@Valid @RequestBody RoomCreateDto room){
        LOGGER.info("POST " + BASE_PATH + "\nBody: {}", room);
        return service.create(room);
    }

    /**
     * Get all rooms
     * @return all rooms
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RoomDetailDto> getAll(){
        LOGGER.info("GET " + BASE_PATH);
        return service.getAll();
    }

    /**
     * Update a room
     * @param id the id of the room to update
     * @param room the room to update
     * @return the updated room
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomDetailDto update(@PathVariable("id") Long id, @Valid @RequestBody RoomDetailDto room){
        LOGGER.info("PUT " + BASE_PATH + "/{}", id);
        return service.update(id, room);
    }
}
