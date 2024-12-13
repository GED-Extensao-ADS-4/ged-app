package br.apae.ged.dto.user;

import java.time.LocalDateTime;

public record UserLoginResponseDTO(
        String token,
        LocalDateTime expiresAt
) {
}
