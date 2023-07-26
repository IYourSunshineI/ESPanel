package com.yoursunshine.backend.endpoint.dto;

import java.awt.*;

public record KnobModuleDetailDto(
        Long id,
        String title,
        Integer pin_number,
        String color,
        Integer brightness
) {
}
