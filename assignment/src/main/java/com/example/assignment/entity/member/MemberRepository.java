package com.example.assignment.entity.member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByName(String name);
}
