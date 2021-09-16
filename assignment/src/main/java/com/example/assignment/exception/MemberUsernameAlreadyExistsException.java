package com.example.assignment.exception;

import com.example.assignment.error.ErrorCode;
import com.example.assignment.error.exception.BusinessException;

public class MemberUsernameAlreadyExistsException extends BusinessException {
    public MemberUsernameAlreadyExistsException() {
        super(ErrorCode.MEMBER_USERNAME_ALREADY_EXISTS);
    }
}
