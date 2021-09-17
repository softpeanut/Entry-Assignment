package com.example.assignment.service.member;

import com.example.assignment.payload.member.request.LoginRequest;
import com.example.assignment.payload.member.request.SignupRequest;
import com.example.assignment.payload.member.request.UpdatePasswordRequest;
import com.example.assignment.payload.member.response.TokenResponse;

public interface MemberService {
    void signup(SignupRequest request);
    TokenResponse login(LoginRequest request);
    TokenResponse reissue(String token);
    void updatePassword(UpdatePasswordRequest request);
    void removeMember(Integer id);
}
