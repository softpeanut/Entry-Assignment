package com.example.assignment.entity.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<BookMark, Integer> {
    List<BookMark> findByMemberId(Integer id);
    Optional<BookMark> findByMemberIdAndFeedId(Integer memberId, Integer postId);
    void deleteByMemberIdAndFeedId(Integer memberId, Integer postId);
}
