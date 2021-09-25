package com.example.assignment.payload.feed.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedRequest {
    private String title;
    private String content;
}
