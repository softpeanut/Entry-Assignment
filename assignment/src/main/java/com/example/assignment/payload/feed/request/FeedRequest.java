package com.example.assignment.payload.feed.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedRequest {
    private String title;
    private String content;
}
