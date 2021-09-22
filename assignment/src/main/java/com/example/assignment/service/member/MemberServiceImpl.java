package com.example.assignment.service.member;

import com.example.assignment.entity.bookmark.BookMarkRepository;
import com.example.assignment.entity.member.Member;
import com.example.assignment.entity.member.MemberRepository;
import com.example.assignment.entity.member.Role;
import com.example.assignment.entity.refreshtoken.RefreshToken;
import com.example.assignment.entity.refreshtoken.RefreshTokenRepository;
import com.example.assignment.exception.*;
import com.example.assignment.facade.MemberFacade;
import com.example.assignment.payload.feed.response.FeedResponse;
import com.example.assignment.payload.member.request.LoginRequest;
import com.example.assignment.payload.member.request.SignupRequest;
import com.example.assignment.payload.member.request.UpdatePasswordRequest;
import com.example.assignment.payload.member.response.TokenResponse;
import com.example.assignment.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Value("${jwt.exp.refresh}")
    private Long refreshTokenExpirationTime;

    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final BookMarkRepository bookMarkRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void signup(SignupRequest request) {

        if(memberRepository.findByName(request.getName()).isPresent())
            throw new MemberNameAlreadyExistsException();
        else if(memberRepository.findByUsername(request.getUsername()).isPresent())
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
    @Transactional
    public TokenResponse login(LoginRequest request) {
        Member member = memberRepository.findByUsername(request.getUsername())
                .orElseThrow(MemberNotFoundException::new);

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new InvalidPasswordException();

        String accessToken = tokenProvider.createAccessToken(request.getUsername());
        String refreshToken = tokenProvider.createRefreshToken(request.getUsername());

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .username(request.getUsername())
                        .refreshToken(refreshToken)
                        .expiration(refreshTokenExpirationTime)
                        .build()
        );

        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public TokenResponse reissue(String refreshToken) {
        if(!tokenProvider.isRefreshToken(refreshToken)
                || !tokenProvider.validateToken(refreshToken))
            throw new InvalidTokenException();

        RefreshToken newRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .map(refresh -> refresh.updateExpiration(refreshTokenExpirationTime))
                .orElseThrow(InvalidTokenException::new);

        return new TokenResponse(tokenProvider.createAccessToken(newRefreshToken.getUsername()), refreshToken);
    }

    @Override
    @Transactional
    public void updatePassword(UpdatePasswordRequest request) {
        Member member = memberRepository.findById(MemberFacade.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new InvalidPasswordException();

        memberRepository.findById(MemberFacade.getMemberId())
                .map(password -> password.updatePassword(passwordEncoder.encode(request.getNewPassword())))
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    @Transactional
    public void removeMember() {
        memberRepository.findById(MemberFacade.getMemberId())
                .orElseThrow(MemberNotFoundException::new);

        memberRepository.deleteById(MemberFacade.getMemberId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedResponse> myBookMarkFeeds() {
        return bookMarkRepository.findByMemberId(MemberFacade.getMemberId())
                .stream()
                .map(bookMark -> {
                    FeedResponse response = FeedResponse.builder()
                            .id(bookMark.getFeed().getId())
                            .title(bookMark.getFeed().getTitle())
                            .content(bookMark.getFeed().getContent())
                            .build();
                    return response;
                })
                .collect(Collectors.toList());
    }

}
