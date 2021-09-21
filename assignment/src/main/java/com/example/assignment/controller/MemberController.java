package com.example.assignment.controller;

import com.example.assignment.payload.member.request.LoginRequest;
import com.example.assignment.payload.member.request.SignupRequest;
import com.example.assignment.payload.member.request.UpdatePasswordRequest;
import com.example.assignment.payload.member.response.TokenResponse;
import com.example.assignment.service.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberServiceImpl memberService;

    @PostMapping("/auth/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@Valid @RequestBody SignupRequest request) {
        memberService.signup(request);
    }

    @PostMapping("/auth/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        return memberService.login(request);
    }

    @PutMapping("/auth/reissue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TokenResponse reissue(@RequestHeader(name = "X-Refresh-Token") String token) {
        return memberService.reissue(token);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePassword(@RequestBody UpdatePasswordRequest request) {
        memberService.updatePassword(request);
    }

    @DeleteMapping("/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMember() {
        memberService.removeMember();
    }

}
