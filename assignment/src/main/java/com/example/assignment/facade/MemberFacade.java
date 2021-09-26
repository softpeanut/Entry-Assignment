package com.example.assignment.facade;

import com.example.assignment.entity.member.Member;
import com.example.assignment.exception.MemberNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class MemberFacade {

    public static Integer getMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getPrincipal() == null)
            throw new MemberNotFoundException();

        Member member = (Member) authentication.getPrincipal();

        return member.getId();
    }

}
