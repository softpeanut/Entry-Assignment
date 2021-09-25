package com.example.assignment.payload.feed.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FeedResponse {
    private Integer id;
    private String title;
    private String content;
}
