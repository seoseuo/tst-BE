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
    private StyleDTO styleDTO;
    private Integer isDelete;
}
