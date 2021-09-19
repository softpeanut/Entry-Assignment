package com.example.assignment.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EXPIRED_TOKEN(401, "Expire Token"),
    INVALID_TOKEN(401, "Invalid Token"),
    INVALID_PASSWORD(401, "Invalid Password"),

    FEED_NOT_FOUND(404, "Feed Not Found"),
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    BOOK_MARK_NOT_FOUND(404, "Bookmark Not Found"),

    BOOK_MARK_ALREADY_EXISTS(409, "Bookmark Already Exists"),
    MEMBER_NAME_ALREADY_EXISTS(409, "Member Name Already Exists"),
    MEMBER_USERNAME_ALREADY_EXISTS(409, "Member Username Already Exists");

    private int status;
    private String message;

}
