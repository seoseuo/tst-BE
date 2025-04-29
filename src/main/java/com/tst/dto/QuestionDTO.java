package com.tst.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private int questionId;    
    private int testId;
    private String questionContent;
    private int isDelete = 1;
}
