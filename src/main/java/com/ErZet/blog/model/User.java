package com.ErZet.blog.model;

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
@Document
public class User {
    @Id
    private ObjectId userId;
    private String fullName;
    private String userName;
    private String password;
    private byte role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}