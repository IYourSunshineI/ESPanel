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
    static final String BASE_PATH = "/groups";
    private final GroupService service;

    public GroupEndpoint(GroupService groupService) {
        this.service = groupService;
    }

    /**
     * Create a new group
     * @param group the group to create
     * @return the created group
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupDetailDto create(@Valid @RequestBody GroupCreateDto group){
        LOGGER.info("POST " + BASE_PATH + "\nBody: {}", group);
        return service.create(group);
    }

    /**
     * Get all groups
     * @return all groups
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDetailDto> getAll(){
        LOGGER.info("GET " + BASE_PATH);
        return service.getAll();
    }

    /**
     * Update a group
     * @param id the id of the group to update
     * @param group the group to update
     * @return the updated group
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDetailDto update(@PathVariable("id") Long id, @Valid @RequestBody GroupDetailDto group){
        LOGGER.info("PUT " + BASE_PATH + "/{}", id);
        return service.update(id, group);
    }

    /**
     * Delete a group
     * @param id the id of the group to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        LOGGER.info("DELETE " + BASE_PATH + "/{}", id);
        service.delete(id);
    }
}
