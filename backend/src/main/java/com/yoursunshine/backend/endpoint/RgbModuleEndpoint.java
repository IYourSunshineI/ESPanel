package com.yoursunshine.backend.endpoint;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.endpoint.dto.RgbModuleCreateDto;
import com.yoursunshine.backend.service.RgbModuleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(value = RgbModuleEndpoint.BASE_PATH)
public class RgbModuleEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    static final String BASE_PATH = "/rooms";
    private final RgbModuleService service;

    public RgbModuleEndpoint(RgbModuleService rgbModuleService) {
        this.service = rgbModuleService;
    }

    @PostMapping("/{room_id}/groups/{group_id}/rgbmodules")
    @ResponseStatus(HttpStatus.CREATED)
    public KnobModuleDetailDto create(@PathVariable("room_id") Long room_id,
                                      @PathVariable("group_id") Long group_id,
                                      @Valid @RequestBody RgbModuleCreateDto rgbModule){
        LOGGER.info("POST " + BASE_PATH + "/" + room_id + "/groups/" + group_id + "/rgbmodules" + "\nBody: {}", rgbModule);
        return service.create(group_id, rgbModule);
    }
}
