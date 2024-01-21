package com.ErZet.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpdateBlogRequest {
    @NotBlank(message = "BlogId is required.")
    private ObjectId blogId;
    @NotBlank(message = "Title is required.")
    private String title;
    @NotBlank(message = "Description is required.")
    private String description;
    @NotNull(message = "Publish is required.")
    private Boolean publish;
    @NotBlank(message = "UserId is required.")
    private ObjectId userId;
}
