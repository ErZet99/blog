package com.ErZet.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DBSResponseEntity<T> {
    T data;
    private String message;
}
