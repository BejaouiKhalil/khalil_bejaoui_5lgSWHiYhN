package com.example.khalil_bejaoui_5lgSWHiYhN.web;

import com.example.khalil_bejaoui_5lgSWHiYhN.web.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ErrorResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
