package com.creativeIdeas.document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


/**
 * refer to MySQL IDs for consistency.
 * Supporting both like and comment in one structure.
 */
@Document(collection = "feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {
    @Id
    private String id;

    private Long ideaId;
    private Long UserId;

    private String type; //like or comment
    private String content;

    private Instant timestamp;
}
