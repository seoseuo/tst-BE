package com.tst.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceDTO {
    private Integer choiceId;
    private QuestionDTO questionDTO;
    private String choiceContent;
    private Integer styleId;
    private Integer isDelete;
}
