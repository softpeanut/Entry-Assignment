package com.example.assignment.exception;

import com.example.assignment.error.ErrorCode;
import com.example.assignment.error.exception.BusinessException;

public class ExpiredAccessTokenException extends BusinessException {
    public ExpiredAccessTokenException() {
        super(ErrorCode.EXPIRED_ACCESS_TOKEN);
    }
}
