package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.exceptions.BusinessException;
import com.hardwareInfo.hardwareInfo.exceptions.DuplicateEmailException;
import com.hardwareInfo.hardwareInfo.exceptions.DuplicateUsernameException;
import com.hardwareInfo.hardwareInfo.models.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler
    ResponseEntity<ErrorDTO> authenticateHandler(BusinessException e) {
        if (e instanceof DuplicateEmailException) {
            return ResponseEntity.status(CONFLICT).body(new ErrorDTO(e.getMessage(), CONFLICT.value()));
        }
        if (e instanceof DuplicateUsernameException) {
            return ResponseEntity.status(CONFLICT).body(new ErrorDTO(e.getMessage(), CONFLICT.value()));
        }
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage(), BAD_REQUEST.value()));
    }
}
