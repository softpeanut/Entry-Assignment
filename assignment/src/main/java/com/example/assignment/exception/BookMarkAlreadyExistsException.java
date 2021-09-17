package com.example.assignment.exception;

import com.example.assignment.error.ErrorCode;
import com.example.assignment.error.exception.BusinessException;

public class BookMarkAlreadyExistsException extends BusinessException {
    public BookMarkAlreadyExistsException() {
        super(ErrorCode.BOOK_MARK_ALREADY_EXISTS);
    }
}
