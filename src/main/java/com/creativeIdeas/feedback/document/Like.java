package com.creativeIdeas.feedback.document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Users can only like once per idea.
 */
@Document(collection = "likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like {
    @Id
    private String id;
    private String ideaId;
    private String username;
}
