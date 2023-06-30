package com.yoursunshine.backend.endpoint.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record GroupDetailDto(
        Long id,
        @NotNull(message = "No title given")
        @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
        String title,
        @Pattern(regexp = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$", message = "Invalid IP address")
        String ip_address,
        boolean state
) {
}
