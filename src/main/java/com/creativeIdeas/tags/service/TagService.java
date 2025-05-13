package com.creativeIdeas.tags.service;

import com.creativeIdeas.tags.dto.TagDTO;
import com.creativeIdeas.tags.entity.Tag;
import com.creativeIdeas.tags.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<TagDTO> getAllTags(){
        return tagRepository.findAll().stream()
                .map(tag -> new TagDTO(tag.getName()))
                .collect(Collectors.toList());
    }

    public void createTag(String tagName){
        if(!tagRepository.existsByName(tagName)){
            tagRepository.save(new Tag(null, tagName));
        }
    }

    public boolean isValidTag(String tagName){
        return tagRepository.existsByName(tagName);
    }

    public void validateTags(List<String> tags){
        for(String tag : tags){
            if(!isValidTag(tag)){
                throw  new IllegalArgumentException("Invalid tag : " + tag);
            }
        }
    }
}
