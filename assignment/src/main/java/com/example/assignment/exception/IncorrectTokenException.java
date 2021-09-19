package com.example.assignment.exception;

import com.example.assignment.error.ErrorCode;
import com.example.assignment.error.exception.BusinessException;

public class IncorrectTokenException extends BusinessException {
    public IncorrectTokenException() {
        super(ErrorCode.INCORRECT_TOKEN);
    }
}
