package com.example.assignment.security.auth;

import com.example.assignment.entity.member.MemberRepository;
import com.example.assignment.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws MemberNotFoundException {
        return memberRepository.findByUsername(username)
                .orElseThrow(MemberNotFoundException::new);
    }

}
