package com.example.assignment.entity.bookmark;

import com.example.assignment.entity.feed.Feed;
import com.example.assignment.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@Builder // AllArgs 필요
@NoArgsConstructor
@AllArgsConstructor
@Entity // NoArgs 필요
public class BookMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Feed feed;
}
