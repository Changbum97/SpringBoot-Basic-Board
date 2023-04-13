package com.study.basicboard.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardSearchRequest {

    private String sortType;
    private String searchType;
    private String keyword;
}
