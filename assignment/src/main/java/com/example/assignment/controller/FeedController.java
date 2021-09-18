package com.example.assignment.controller;

import com.example.assignment.payload.feed.request.FeedRequest;
import com.example.assignment.payload.feed.response.FeedResponse;
import com.example.assignment.service.feed.FeedServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    private final FeedServiceImpl feedService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFeed(@RequestBody FeedRequest request) {
        feedService.createFeed(request);
    }

    @GetMapping("/show")
    public List<FeedResponse> showAllFeeds() {
        return feedService.showAllFeeds();
    }

    @GetMapping("/show/{feed-id}")
    public FeedResponse showFeed(@PathVariable(name = "feed-id") Integer id) {
        return feedService.showFeed(id);
    }

    @PatchMapping("/{feed-id}")
    public void updateFeed(@PathVariable(name = "feed-id") Integer id,
                           @RequestBody FeedRequest request) {
        feedService.updateFeed(id, request);
    }

    @DeleteMapping("/{feed-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFeed(@PathVariable(name = "feed-id") Integer id) {
        feedService.removeFeed(id);
    }

    @PostMapping("/bookmark/{feed-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBookMark(@PathVariable(name = "feed-id") Integer id) {
        feedService.addBookMark(id);
    }

    @DeleteMapping("/bookmark/{feed-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookMark(@PathVariable(name = "feed-id") Integer id) {
        feedService.removeBookMark(id);
    }

    @GetMapping("/bookmarks")
    public List<FeedResponse> myBookMarkFeeds() {
        return feedService.myBookMarkFeeds();
    }

}
