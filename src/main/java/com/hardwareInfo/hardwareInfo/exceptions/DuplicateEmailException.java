package com.hardwareInfo.hardwareInfo.exceptions;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
