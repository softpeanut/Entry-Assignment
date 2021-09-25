package com.example.assignment.payload.member.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor // 역직렬화
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
