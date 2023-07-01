package com.yoursunshine.backend.endpoint;

import com.yoursunshine.backend.endpoint.dto.GroupCreateDto;
import com.yoursunshine.backend.endpoint.dto.GroupDetailDto;
import com.yoursunshine.backend.service.GroupService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(value = GroupEndpoint.BASE_PATH)
public class GroupEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    static final String BASE_PATH = "/rooms";
    private final GroupService service;

    public GroupEndpoint(GroupService groupService) {
        this.service = groupService;
    }

    /**
     * Create a new group
     * @param group the group to create
     * @return the created group
     */
    @PostMapping("/{room_id}/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupDetailDto create(@PathVariable("room_id") Long room_id, @Valid @RequestBody GroupCreateDto group){
        LOGGER.info("POST " + BASE_PATH + "/" + room_id + "/groups" + "\nBody: {}", group);
        return service.create(room_id, group);
    }

    /**
     * Get all groups
     * @return all groups
     */
    @GetMapping("/{room_id}/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDetailDto> getAll(@PathVariable("room_id") Long room_id){
        LOGGER.info("GET " + BASE_PATH + "/" + room_id + "/groups");
        return service.getAll(room_id);
    }

    /**
     * Update a group
     * @param id the id of the group to update
     * @param group the group to update
     * @return the updated group
     */
    @PutMapping("/{room_id}/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDetailDto update(@PathVariable("room_id") Long room_id, @PathVariable("id") Long id, @Valid @RequestBody GroupDetailDto group){
        LOGGER.info("PUT " + BASE_PATH + "/{}/groups/{}", room_id, id);
        return service.update(id, group);
    }

    /**
     * Delete a group
     * @param id the id of the group to delete
     */
    @DeleteMapping("/{room_id}/groups/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("room_id") Long room_id, @PathVariable("id") Long id){
        LOGGER.info("DELETE " + BASE_PATH + "/{}/groups/{}", room_id, id);
        service.delete(id);
    }
}
