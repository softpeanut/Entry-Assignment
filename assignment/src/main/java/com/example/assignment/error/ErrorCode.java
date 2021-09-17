package com.example.assignment.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MEMBER_NAME_ALREADY_EXISTS(409, "Member Name Already Exists"),
    MEMBER_USERNAME_ALREADY_EXISTS(409, "Member Username Already Exists"),
    MEMBER_NOT_FOUND(404, "Member Not Found"),

    INVALID_PASSWORD(401, "Invalid Password"),
    INVALID_TOKEN(401, "Invalid Token"),

    FEED_NOT_FOUND(404, "Feed Not Found"),

    BOOK_MARK_ALREADY_EXISTS(409, "Book Mark Already Exists");

    private int status;
    private String message;

}
