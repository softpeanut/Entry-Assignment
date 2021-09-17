package com.example.assignment.exception;

import com.example.assignment.error.ErrorCode;
import com.example.assignment.error.exception.BusinessException;

public class FeedNotFoundException extends BusinessException {
    public FeedNotFoundException() {
        super(ErrorCode.FEED_NOT_FOUND);
    }
}
