package com.example.assignment.service.feed;

import com.example.assignment.entity.bookmark.BookMark;
import com.example.assignment.entity.bookmark.BookMarkRepository;
import com.example.assignment.entity.feed.Feed;
import com.example.assignment.entity.feed.FeedRepository;
import com.example.assignment.entity.member.Member;
import com.example.assignment.entity.member.MemberRepository;
import com.example.assignment.exception.BookMarkAlreadyExistsException;
import com.example.assignment.exception.BookMarkNotFoundException;
import com.example.assignment.exception.FeedNotFoundException;
import com.example.assignment.exception.MemberNotFoundException;
import com.example.assignment.facade.MemberFacade;
import com.example.assignment.payload.feed.request.FeedRequest;
import com.example.assignment.payload.feed.response.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;
    private final BookMarkRepository bookMarkRepository;

    @Override
    @Transactional
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
    @Transactional(readOnly = true)
    public List<FeedResponse> showAllFeeds() {
        return feedRepository.findAll()
                .stream()
                .map(feed -> {
                    FeedResponse response = FeedResponse.builder()
                            .id(feed.getId())
                            .title(feed.getTitle())
                            .content(feed.getContent())
                            .build();
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FeedResponse showFeed(Integer id) {
        return feedRepository.findById(id)
                .map(feed -> new FeedResponse(feed.getId(), feed.getTitle(), feed.getContent()))
                .orElseThrow(FeedNotFoundException::new);
    }

    @Override
    @Transactional
    public void updateFeed(Integer id, FeedRequest request) {
        feedRepository.findById(id)
                .map(feed -> feedRepository.save(
                        feed.update(request.getTitle(), request.getContent())
                ))
                .orElseThrow(FeedNotFoundException::new);
    }

    @Override
    @Transactional
    public void removeFeed(Integer id) {
        feedRepository.findById(id)
                .orElseThrow(FeedNotFoundException::new);

        feedRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addBookMark(Integer id) {
        Member member = memberRepository.findById(MemberFacade.getCurrentMemberId())
                .orElseThrow(MemberNotFoundException::new);

        Feed feed = feedRepository.findById(id)
                .orElseThrow(FeedNotFoundException::new);

        if(bookMarkRepository.findByMemberIdAndFeedId(member.getId(), id).isPresent())
            throw new BookMarkAlreadyExistsException();

        bookMarkRepository.save(BookMark.builder()
                .member(member)
                .feed(feed)
                .build());
    }

    @Override
    @Transactional
    public void removeBookMark(Integer id) {
        BookMark bookMark = bookMarkRepository.findByMemberIdAndFeedId(MemberFacade.getCurrentMemberId(), id)
                .orElseThrow(BookMarkNotFoundException::new);

        bookMarkRepository.deleteById(bookMark.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedResponse> myBookMarkFeeds() {
        return bookMarkRepository.findByMemberId(MemberFacade.getCurrentMemberId())
                .stream()
                .map(bookMark -> {
                    FeedResponse response = FeedResponse.builder()
                            .id(bookMark.getFeed().getId())
                            .title(bookMark.getFeed().getTitle())
                            .content(bookMark.getFeed().getContent())
                            .build();
                    return response;
                })
                .collect(Collectors.toList());
    }

}
