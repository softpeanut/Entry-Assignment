package com.example.assignment.payload.member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePasswordRequest {
    private String password;
    private String newPassword;
}
