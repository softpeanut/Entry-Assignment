package com.example.assignment.exception;

import com.example.assignment.error.ErrorCode;
import com.example.assignment.error.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
