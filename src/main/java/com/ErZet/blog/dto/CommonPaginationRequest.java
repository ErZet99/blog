package com.ErZet.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonPaginationRequest {
    private Integer pegeNo;
    private Integer pageSize;
    private String sortBy;
    private String value;
}
