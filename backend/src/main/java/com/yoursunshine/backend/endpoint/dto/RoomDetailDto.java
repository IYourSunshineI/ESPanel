package com.yoursunshine.backend.endpoint.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RoomDetailDto(
        @NotNull(message = "No id given")
        Long id,
        @NotNull(message = "No title given")
        @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
        String title
) {
}
