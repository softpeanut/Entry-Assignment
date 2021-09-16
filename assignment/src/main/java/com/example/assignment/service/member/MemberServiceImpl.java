package com.example.assignment.service.member;

import com.example.assignment.entity.member.Member;
import com.example.assignment.entity.member.MemberRepository;
import com.example.assignment.entity.member.Role;
import com.example.assignment.entity.refreshtoken.RefreshToken;
import com.example.assignment.entity.refreshtoken.RefreshTokenRepository;
import com.example.assignment.exception.*;
import com.example.assignment.payload.member.request.LoginRequest;
import com.example.assignment.payload.member.request.SignupRequest;
import com.example.assignment.payload.member.response.TokenResponse;
import com.example.assignment.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Value("${jwt.exp.refresh}")
    private Long refreshTokenExpirationTime;

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider tokenProvider;

    @Override
    public void signup(SignupRequest request) {

        if(memberRepository.findByName(request.getName()) == null)
            throw new MemberNameAlreadyExistsException();
        else if(memberRepository.findByUsername(request.getUsername()) == null)
            throw new MemberUsernameAlreadyExistsException();

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
        Member member = memberRepository.findByUsername(request.getUsername())
                .orElseThrow(MemberNotFoundException::new);

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new InvalidPasswordException();

        return createToken(request.getUsername());

    }

    @Override
    public TokenResponse reissue(String refreshToken) {
        if(!tokenProvider.isRefreshToken(refreshToken) || !tokenProvider.validateToken(refreshToken))
            throw new InvalidTokenException();

        RefreshToken newRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .map(token -> token.updateExpiration(refreshTokenExpirationTime))
                .orElseThrow();

        return new TokenResponse(tokenProvider.createAccessToken(newRefreshToken.getUsername()), refreshToken);
    }

    public TokenResponse createToken(String username) {
        String accessToken = tokenProvider.createAccessToken(username);
        String refreshToken = tokenProvider.createRefreshToken(username);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .username(username)
                        .refreshToken(refreshToken)
                        .expiration(refreshTokenExpirationTime)
                        .build()
        );

        return new TokenResponse(accessToken, refreshToken);
    }

}
