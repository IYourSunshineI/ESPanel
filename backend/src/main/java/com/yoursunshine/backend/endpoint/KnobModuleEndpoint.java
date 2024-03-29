package com.yoursunshine.backend.endpoint;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.service.KnobModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(value = KnobModuleEndpoint.BASE_PATH)
public class KnobModuleEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    static final String BASE_PATH = "/groups";
    private final KnobModuleService service;

    public KnobModuleEndpoint(KnobModuleService knobModuleService) {
        this.service = knobModuleService;
    }

    /**
     * Get all knob modules from a group
     * @return all knob modules
     */
    @GetMapping("/{group_id}/knobmodules")
    @ResponseStatus(HttpStatus.OK)
    public List<KnobModuleDetailDto> getAll(@PathVariable("group_id") Long group_id){
        LOGGER.info("GET " + BASE_PATH + "/{}/knobmodules", group_id);
        return service.getAll(group_id);
    }

    /**
     * Delete a knob module
     * @param id the id of the knob module to delete
     */
    @DeleteMapping("/{group_id}/knobmodules/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("group_id") Long group_id,
                       @PathVariable("id") Long id){
        LOGGER.info("DELETE " + BASE_PATH + "/{}/knobmodules/{}", group_id, id);
        service.delete(id);
    }

}
