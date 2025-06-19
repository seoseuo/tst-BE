package com.tst.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Integer questionId;
    private TestDTO testDTO;
    private String questionContent;
    private Integer isDelete;
}
