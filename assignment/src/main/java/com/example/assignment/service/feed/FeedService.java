package com.example.assignment.service.feed;

import com.example.assignment.payload.feed.request.FeedRequest;
import com.example.assignment.payload.feed.response.FeedResponse;
import java.util.List;

public interface FeedService {
    void createFeed(FeedRequest request);
    List<FeedResponse> showAllFeeds();
    FeedResponse showFeed(Integer id);
    void updateFeed(Integer id, FeedRequest request);
    void deleteFeed(Integer id);
}
