package com.example.assignment.exception;

import com.example.assignment.error.ErrorCode;
import com.example.assignment.error.exception.BusinessException;

public class BookMarkNotFoundException extends BusinessException {
    public BookMarkNotFoundException() {
        super(ErrorCode.BOOK_MARK_NOT_FOUND);
    }
}
