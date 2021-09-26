package com.example.assignment.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 토큰
    INCORRECT_TOKEN(400, "Incorrect Token"),
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_ACCESS_TOKEN(401, "Expired Access Token"),
    EXPIRED_REFRESH_TOKEN(401, "Expired Refresh Token"),

    // 회원
    INVALID_PASSWORD(401, "Invalid Password"),
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_NAME_ALREADY_EXISTS(409, "Member Name Already Exists"),
    MEMBER_USERNAME_ALREADY_EXISTS(409, "Member Username Already Exists"),

    // 게시글
    FEED_NOT_FOUND(404, "Feed Not Found"),

    // 북마크
    BOOK_MARK_NOT_FOUND(404, "Bookmark Not Found"),
    BOOK_MARK_ALREADY_EXISTS(409, "Bookmark Already Exists");

    private int status;
    private String message;

}
