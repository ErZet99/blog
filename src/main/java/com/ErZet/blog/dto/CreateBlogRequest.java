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
public class CreateBlogRequest {
    private String title;
    private String description;
    private Boolean publish;
    private String userId;
}
