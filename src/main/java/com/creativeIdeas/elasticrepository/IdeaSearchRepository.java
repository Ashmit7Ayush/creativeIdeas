package com.creativeIdeas.elasticrepository;

import com.creativeIdeas.elastic.IdeaDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * basic search queries on indexed ideas.
 */
public interface IdeaSearchRepository extends ElasticsearchRepository<IdeaDocument, String> {
    List<IdeaDocument> findByTitleContainingOrContentContaining(String title, String content);
}
