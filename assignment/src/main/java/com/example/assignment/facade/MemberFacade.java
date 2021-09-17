package com.example.assignment.facade;

import com.example.assignment.entity.member.Member;
import com.example.assignment.exception.MemberNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberFacade {

    public static Integer getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getPrincipal() == null)
            throw new MemberNotFoundException();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = (Member) userDetails;

        return member.getId();
    }

}
