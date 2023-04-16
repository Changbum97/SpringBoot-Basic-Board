package com.study.basicboard.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardCntDto {

    private Long totalNoticeCnt;
    private Long totalBoardCnt;
    private Long totalGreetingCnt;
    private Long totalFreeCnt;
    private Long totalGoldCnt;
}
