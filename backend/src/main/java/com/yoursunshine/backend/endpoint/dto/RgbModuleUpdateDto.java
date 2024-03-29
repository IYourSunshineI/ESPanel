package com.yoursunshine.backend.endpoint.dto;

import jakarta.validation.constraints.*;

public record RgbModuleUpdateDto(
        @NotNull(message = "No id given")
        Long id,
        @NotNull(message = "No title given")
        @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
        String title,
        @NotNull(message = "No pin number given")
        @Min(value = 0, message = "Pin Number must be between 0 and 16")
        @Max(value = 16, message = "Pin Number must be between 0 and 16")
        Integer pinNumber,
        @NotNull(message = "No color given")
        @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Invalid color")
        String color,
        @NotNull(message = "No group given")
        Long group_id
) {
}
