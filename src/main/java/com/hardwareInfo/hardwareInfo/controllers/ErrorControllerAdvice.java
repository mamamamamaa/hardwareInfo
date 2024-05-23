package com.hardwareInfo.hardwareInfo.controllers;

import com.hardwareInfo.hardwareInfo.exceptions.*;
import com.hardwareInfo.hardwareInfo.models.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler
    ResponseEntity<ErrorDTO> exceptionHandler(BusinessException e) {
        if (e instanceof DuplicateEmailException) {
            return ResponseEntity.status(CONFLICT).body(new ErrorDTO(e.getMessage(), CONFLICT.value()));
        }
        if (e instanceof DuplicateUsernameException) {
            return ResponseEntity.status(CONFLICT).body(new ErrorDTO(e.getMessage(), CONFLICT.value()));
        }
        if (e instanceof ApiSupportsNotFoundException) {
            return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(e.getMessage(), NOT_FOUND.value()));
        }
        if (e instanceof ArchitectureNotFoundException) {
            return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(e.getMessage(), NOT_FOUND.value()));
        }
        if (e instanceof BenchmarkResultNotFoundException) {
            return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(e.getMessage(), NOT_FOUND.value()));
        }
        if (e instanceof DieSizeNotFoundException) {
            return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(e.getMessage(), NOT_FOUND.value()));
        }
        if (e instanceof VendorNotFoundException) {
            return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(e.getMessage(), NOT_FOUND.value()));
        }
        if (e instanceof VramTypeNotFoundException) {
            return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(e.getMessage(), NOT_FOUND.value()));
        }
        if (e instanceof VramCapacityNotFoundException) {
            return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(e.getMessage(), NOT_FOUND.value()));
        }
        if (e instanceof SubVendorNotFoundException) {
            return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(e.getMessage(), NOT_FOUND.value()));
        }
        if (e instanceof TdpNotFoundException) {
            return ResponseEntity.status(NOT_FOUND).body(new ErrorDTO(e.getMessage(), NOT_FOUND.value()));
        }
        return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage(), BAD_REQUEST.value()));
    }
}
