package com.tst.dto;

import lombok.Data;

@Data
public class ChoiceDTO {
    private int choiceId;
    private int questionId;
    private int testId;
    private String choiceContent;
    private int styleId1;
    private int styleId2;
    private int isDelete = 1;
}
