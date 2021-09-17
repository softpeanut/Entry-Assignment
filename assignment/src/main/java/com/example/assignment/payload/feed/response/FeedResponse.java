package com.example.assignment.payload.feed.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NotEmpty
@AllArgsConstructor
public class FeedResponse {
    private String title;
    private String content;
}
