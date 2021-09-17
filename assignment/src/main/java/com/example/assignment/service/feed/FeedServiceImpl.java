package com.example.assignment.service.feed;

import com.example.assignment.entity.feed.Feed;
import com.example.assignment.entity.feed.FeedRepository;
import com.example.assignment.entity.member.Member;
import com.example.assignment.entity.member.MemberRepository;
import com.example.assignment.exception.MemberNotFoundException;
import com.example.assignment.facade.MemberFacade;
import com.example.assignment.payload.feed.request.FeedRequest;
import com.example.assignment.payload.feed.response.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;

    @Override
    public void createFeed(FeedRequest request) {

        Member member = memberRepository.findById(MemberFacade.getCurrentMemberId())
                .orElseThrow(MemberNotFoundException::new);

        feedRepository.save(Feed.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(member)
                .build()
        );
    }

    @Override
    public List<FeedResponse> showAllFeeds() {
        return feedRepository.findAll()
                .stream()
                .map(feed -> {
                    FeedResponse response = FeedResponse.builder()
                            .title(feed.getTitle())
                            .content(feed.getContent())
                            .build();
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public FeedResponse showFeed(Integer id) {
        return feedRepository.findById(id)
                .map(feed -> new FeedResponse(feed.getTitle(), feed.getContent()))
                .orElseThrow();
    }

    @Override
    public void updateFeed(Integer id, FeedRequest request) {
        feedRepository.findById(id)
                .map(feed -> feedRepository.save(
                        feed.update(request.getTitle(), request.getContent())
                ))
                .orElseThrow();
    }

    @Override
    public void deleteFeed(Integer id) {
        feedRepository.deleteById(id);
    }

}
