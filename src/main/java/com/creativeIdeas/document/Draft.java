package com.creativeIdeas.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Draft {
    @Id
    private String id;

    private Long authorId;
    private Long ideaId;

    private String title;
    private String content;

    private List<String> tags;

    private String status;
    private Instant lastEdited;
}
