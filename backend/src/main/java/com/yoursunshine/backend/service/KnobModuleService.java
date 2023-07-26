package com.yoursunshine.backend.service;

import com.yoursunshine.backend.endpoint.dto.KnobModuleDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KnobModuleService {

    /**
     * Get all knob modules
     * @return all knob modules
     */
    List<KnobModuleDetailDto> getAll();
}
