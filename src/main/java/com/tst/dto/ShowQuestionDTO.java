package com.tst.dto;

import com.tst.dto.QuestionDTO;
import com.tst.dto.ChoiceDTO;
import lombok.Data;
import java.util.List;

@Data
public class ShowQuestionDTO {
    private List<QuestionDTO> questions;
    private List<ChoiceDTO> options;  
}
