package com.guilhermepereira.urlshortener.dto;

import java.time.LocalDateTime;

public record ShortenUrlResponse(String url, LocalDateTime expiresAt) {
}
