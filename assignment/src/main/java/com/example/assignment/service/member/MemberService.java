package com.example.assignment.service.member;

import com.example.assignment.payload.feed.response.FeedResponse;
import com.example.assignment.payload.member.request.LoginRequest;
import com.example.assignment.payload.member.request.SignupRequest;
import com.example.assignment.payload.member.request.UpdatePasswordRequest;
import com.example.assignment.payload.member.response.TokenResponse;
import java.util.List;

public interface MemberService {
    void signup(SignupRequest request);
    TokenResponse login(LoginRequest request);
    TokenResponse reissue(String token);
    void updatePassword(UpdatePasswordRequest request);
    void removeMember();
    List<FeedResponse> myBookMarkFeeds();
}
