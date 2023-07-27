package com.yoursunshine.backend.endpoint;

import com.yoursunshine.backend.endpoint.dto.DimmerModuleCreateDto;
import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import com.yoursunshine.backend.service.DimmerModuleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(value = DimmerModuleEndpoint.BASE_PATH)
public class DimmerModuleEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    static final String BASE_PATH = "/rooms";
    private final DimmerModuleService service;

    public DimmerModuleEndpoint(DimmerModuleService dimmerModuleService) {
        this.service = dimmerModuleService;
    }

    /**
     * Create a new dimmer module
     * @param dimmerModule the dimmer module to create
     * @return the created dimmer module
     */
    @PostMapping("/{room_id}/groups/{group_id}/dimmermodules")
    @ResponseStatus(HttpStatus.CREATED)
    public KnobModuleDetailDto create(@PathVariable("room_id") Long room_id,
                                      @PathVariable("group_id") Long group_id,
                                      @Valid @RequestBody DimmerModuleCreateDto dimmerModule){
        LOGGER.info("POST " + BASE_PATH + "/" + room_id + "/groups/" + group_id + "/dimmermodules" + "\nBody: {}", dimmerModule);
        return service.create(group_id, dimmerModule);
    }
}
