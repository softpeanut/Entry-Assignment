package com.example.assignment.entity.feed;

import com.example.assignment.entity.BaseTimeEntity;
import com.example.assignment.entity.bookmark.BookMark;
import com.example.assignment.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Feed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<BookMark> bookMarks;

    public Feed update(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }

}
