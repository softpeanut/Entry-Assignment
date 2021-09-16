package com.example.assignment.exception;

import com.example.assignment.error.ErrorCode;
import com.example.assignment.error.exception.BusinessException;

public class MemberNameAlreadyExistsException extends BusinessException {
    public MemberNameAlreadyExistsException() {
        super(ErrorCode.MEMBER_NAME_ALREADY_EXISTS);
    }
}
