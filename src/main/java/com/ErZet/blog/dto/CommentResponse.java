package com.ErZet.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {
    @Id
    private String commentId;
    private String title;
    private ObjectId userId;
    private ObjectId blogId;
    private LocalDateTime createdAt;
    public LocalDateTime updatedAd;
}
