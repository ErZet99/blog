package com.ErZet.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)  // if data or messee is null, then not include
public class DBSResponseEntity<T> {
    T data;
    private String message;
}
