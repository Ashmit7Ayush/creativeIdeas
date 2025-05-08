package com.creativeIdeas.search.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.creativeIdeas.search.dto.SearchRequestDto;
import com.creativeIdeas.search.dto.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final ElasticsearchClient elasticsearchClient;
    private final String index = "ideas";


    public List<SearchResponseDto> searchIdeas(SearchRequestDto searchRequestDto) throws IOException {
        var boolQuery = new BoolQuery.Builder();

        //full text + fuzzy search
        if(searchRequestDto.getQuery()!=null && !searchRequestDto.getQuery().isEmpty()){
            boolQuery.must(mq -> mq
                    .multiMatch(m -> m
                            .fields("title", "content")
                            .query(searchRequestDto.getQuery())
                            .fuzziness("AUTO")
                    )
            );
        }


        // Filters
        if(searchRequestDto.getDomain()!=null){
            boolQuery.filter(f -> f.term(t -> t.field("domain").value(searchRequestDto.getDomain())));
        }

        if(searchRequestDto.getAuthorUsername()!=null){
            boolQuery.filter(f -> f.term(t -> t.field("authorUsername").value(searchRequestDto.getAuthorUsername())));
        }

        if(searchRequestDto.getStatus()!=null){
            boolQuery.filter(f -> f.term(t -> t.field("status").value(searchRequestDto.getStatus())));
        }

        if(searchRequestDto.getTags()!=null && !searchRequestDto.getTags().isEmpty()){
            List<FieldValue> fieldValueList = searchRequestDto.getTags().stream().map(FieldValue::of).toList();
            boolQuery.filter(f -> f.terms(t -> t.field("tags").terms(ts -> ts.value(fieldValueList))));
        }

        SortOrder sortOrder = searchRequestDto.getSortOrder().equalsIgnoreCase("asc") ? SortOrder.Asc : SortOrder.Desc;

        int from = searchRequestDto.getPage() * searchRequestDto.getSize();

        //search request
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(index)
                .from(from)
                .size(searchRequestDto.getSize())
                .query(q -> q.bool(boolQuery.build()))
                .sort(s -> s.field(sf -> sf
                        .field(searchRequestDto.getSortBy())
                        .order(sortOrder)))
                .build();

        SearchResponse<Map> searchResponse = elasticsearchClient.search(searchRequest, Map.class);

        List<SearchResponseDto> searchResponseDtoList = new ArrayList<>();
        for(Hit<Map> hit : searchResponse.hits().hits()){
            Map<String, Object> source = hit.source();

            SearchResponseDto searchResponseDto = SearchResponseDto.builder()
                    .id((String) source.get("id"))
                    .title((String) source.get("title"))
                    .authorUsername((String) source.get("authorUsername"))
                    .tags((List<String>) source.get("tags"))
                    .domain((String) source.get("domain"))
                    .popularityScore(Double.valueOf(source.get("popularityScore").toString()))
                    .createdAt((Instant) source.get("createdAt"))
                    .status((String) source.get("status"))
                    .build();

            searchResponseDtoList.add(searchResponseDto);
        }

    return searchResponseDtoList;
    }
}
