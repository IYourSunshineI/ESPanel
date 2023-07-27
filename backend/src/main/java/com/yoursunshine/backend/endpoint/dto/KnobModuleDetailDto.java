package com.yoursunshine.backend.endpoint.dto;

import java.awt.*;

public record KnobModuleDetailDto(
        Long id,
        String title,
        Integer pinNumber,
        String color,
        Integer brightness,
        Long group_id
) {
}
