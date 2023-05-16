package com.example.khalil_bejaoui_5lgSWHiYhN.web.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponseDTO(String message, HttpStatus status) {
}
