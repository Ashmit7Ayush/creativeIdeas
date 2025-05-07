package com.creativeIdeas.indexing.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.creativeIdeas.indexing.dto.ElasticIdeaDto;
import com.creativeIdeas.ideas.entity.Idea;
import com.creativeIdeas.indexing.mapper.ElasticIdeaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class IdeaIndexService {
    private final ElasticsearchClient esClient;
    private final ElasticIdeaMapper elasticIdeaMapper;
    private static final String INDEX = "index";

    //we have used the Elasticsearch Java API Client, which is more future-proof and preferred over Spring Data Elasticsearch.
    // so no IdeaDocuments and it's repository
    public void indexIdea(Idea idea){
        ElasticIdeaDto elasticIdeaDto = elasticIdeaMapper.toDto(idea);
        try{
            esClient.index(i -> i
                            .index(INDEX)
                            .id(idea.getId().toString())
                            .document(elasticIdeaDto)
                    );
        }catch (IOException e){
            throw new RuntimeException("Failed to index idea", e);
        }
    }



    public void deleteIndex(Long ideaId){
        try{
            esClient.delete(i -> i
                    .index(INDEX)
                    .id(ideaId.toString())
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete idea from index", e);
        }
    }

    private String getLatestVersionContent(Idea idea) {
        //todo : extract
        return idea.getTitle() + "-test";
    }
}
