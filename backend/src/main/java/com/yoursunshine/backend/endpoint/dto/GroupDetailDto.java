package com.yoursunshine.backend.endpoint.dto;

public record GroupDetailDto(
        Long id,
        String title,
        String ip_address,
        boolean state
) {
}
