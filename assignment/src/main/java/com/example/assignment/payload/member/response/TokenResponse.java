package com.example.assignment.payload.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 직렬화
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
