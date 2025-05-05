package com.creativeIdeas.elastic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.List;

@Document(indexName = "ideas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaDocument {
    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String content;

    @Field(type = FieldType.Keyword)
    private String authorUsername;

    @Field(type = FieldType.Keyword)
    private List<String> tags;

    @Field(type = FieldType.Keyword)
    private String domain;

    @Field(type = FieldType.Keyword)
    private String status; // DRAFTY/PUBLISHED/ARCHIVED

    @Field(type = FieldType.Double)
    private Double popularityScore;

    @Field(type = FieldType.Date)
    private Instant createdAt;

}
