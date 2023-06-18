package com.yoursunshine.backend.endpoint.dto;

import jakarta.validation.constraints.NotNull;

public record RoomCreateDto(
        @NotNull(message = "No title given")
        String title
) {
}
