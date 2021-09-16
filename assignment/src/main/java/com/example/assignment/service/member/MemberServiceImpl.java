package com.example.assignment.service.member;

import com.example.assignment.entity.member.Member;
import com.example.assignment.entity.member.MemberRepository;
import com.example.assignment.entity.member.Role;
import com.example.assignment.payload.member.request.LoginRequest;
import com.example.assignment.payload.member.request.SignupRequest;
import com.example.assignment.payload.member.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public void signup(SignupRequest request) {
        memberRepository.save(
                Member.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build()
        );

    }

    @Override
    public TokenResponse login(LoginRequest request) {

    }

    @Override
    public TokenResponse reissue(String token) {

    }

}
