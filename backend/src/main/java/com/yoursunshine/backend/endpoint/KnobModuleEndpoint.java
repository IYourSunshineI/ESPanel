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
    static final String BASE_PATH = "/rooms";
    private final KnobModuleService service;

    public KnobModuleEndpoint(KnobModuleService knobModuleService) {
        this.service = knobModuleService;
    }

    /**
     * Get all knob modules
     * @return all knob modules
     */
    @GetMapping("/{room_id}/groups/{group_id}/knobmodules")
    @ResponseStatus(HttpStatus.OK)
    public List<KnobModuleDetailDto> getAll(@PathVariable("room_id") Long room_id,
                                            @PathVariable("group_id") Long group_id){
        LOGGER.info("GET " + BASE_PATH);
        return service.getAll();
    }
}
