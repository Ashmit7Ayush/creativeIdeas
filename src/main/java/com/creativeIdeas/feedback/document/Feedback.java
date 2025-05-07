package com.creativeIdeas.feedback.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

/**
 * ideaId: idea this feedback belongs to.
 * parentId: to create reply chains.
 * replies: List of nested reply IDs.
 */

@Document(collection = "feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {
    @Id
    private String id;
    private String ideaId;
    private String authorUsername;
    private String content; // comment text
    private Instant createdAt;
    private String parentId; // for replies
    private List<String> replies; // nested replies(child feedback IDs)
}
