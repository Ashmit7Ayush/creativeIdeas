package com.creativeIdeas.feedback.service.impl;

import com.creativeIdeas.feedback.document.Feedback;
import com.creativeIdeas.feedback.document.Like;
import com.creativeIdeas.feedback.dto.FeedbackRequestDto;
import com.creativeIdeas.feedback.dto.FeedbackResponseDto;
import com.creativeIdeas.feedback.mongorepository.FeedbackRepository;
import com.creativeIdeas.feedback.mongorepository.LikeRepository;
import com.creativeIdeas.feedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final LikeRepository likeRepository;

    @Override
    public FeedbackResponseDto addFeedback(String ideaId, String username, FeedbackRequestDto feedbackRequestDto){
        Feedback feedback = Feedback.builder()
                .ideaId(ideaId)
                .content(feedbackRequestDto.getContent())
                .parentId(feedbackRequestDto.getParentId())
                .authorUsername(username)
                .createdAt(new Date().toInstant())
                .build();

        Feedback saved =  feedbackRepository.save(feedback);

        // also need to check whether it's a reply, if it is then need to update the replies of the parentId's feedback
        if(feedbackRequestDto.getParentId()!=null){
            feedbackRepository.findById(feedbackRequestDto.getParentId()).ifPresent(parent -> {
                        List<String> replies = parent.getReplies();
                        replies.add(saved.getId());
                        parent.setReplies(replies);
                        feedbackRepository.save(parent);
                    });
        }

        return toDto(saved);
    }

    @Override
    public List<FeedbackResponseDto> getFeedbackForIdea(String ideaId){
        List<Feedback> feedbackList = feedbackRepository.findByIdeaId(ideaId);

        // now map with id to feedbackResponseDto
        Map<String, FeedbackResponseDto> feedbackResponseDtoMap = feedbackList.stream()
                .map(this::toDto)
                .collect(Collectors.toMap(FeedbackResponseDto::getId, dto-> dto));

        // now attaching the all replies according to their parents
        // also need to make a set with feedback id whose parentId is not null;
        Set<String> feedbackWithParentId = new HashSet<>();
        for(Feedback feedback : feedbackList){
            if(feedback.getParentId()!=null){
                feedbackWithParentId.add(feedback.getId());
                FeedbackResponseDto parentDto = feedbackResponseDtoMap.get(feedback.getParentId());
                if(parentDto!=null){
                    if(parentDto.getReplies()==null){
                        parentDto.setReplies(new ArrayList<>());
                    }
                    parentDto.getReplies().add(feedbackResponseDtoMap.get(feedback.getId()));
                }
            }
        }

        // only need to return the feedbackResponseDto with null parentId
        return feedbackResponseDtoMap.values().stream()
                .filter(feed -> feed.getReplies()!=null || feedbackWithParentId.contains(feed.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void likeIdea(String ideaId, String username){
        // if already liked by this username then no action else add the like
        likeRepository.findByIdeaIdAndUsername(ideaId, username).ifPresentOrElse(
                like -> {}, // already liked
                () -> likeRepository.save(Like.builder().ideaId(ideaId).username(username).build())
        );
    }

    @Override
    public long getLikeCount(String ideaId){
        return likeRepository.countByIdeaId(ideaId);
    }

    private FeedbackResponseDto toDto(Feedback feedback){
        FeedbackResponseDto feedbackResponseDto = new FeedbackResponseDto();
        feedbackResponseDto.setId(feedback.getId());
        feedbackResponseDto.setIdeaId(feedback.getIdeaId());
        feedbackResponseDto.setContent(feedback.getContent());
        feedbackResponseDto.setAuthor(feedback.getAuthorUsername());
        feedbackResponseDto.setCreatedAt(feedback.getCreatedAt());

        return feedbackResponseDto;
    }
}
