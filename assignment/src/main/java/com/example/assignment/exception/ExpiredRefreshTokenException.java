package com.example.assignment.exception;

import com.example.assignment.error.ErrorCode;
import com.example.assignment.error.exception.BusinessException;

public class ExpiredRefreshTokenException extends BusinessException {
    public ExpiredRefreshTokenException() {
        super(ErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}
