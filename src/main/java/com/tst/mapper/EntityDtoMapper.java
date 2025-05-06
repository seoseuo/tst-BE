package com.tst.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.tst.dto.*;
import com.tst.entity.*;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {

    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    // Test 엔티티와 DTO 변환
    TestDTO toTestDTO(Test test);

    Test toTestEntity(TestDTO testDTO);

    // Question 엔티티와 DTO 변환
    QuestionDTO toQuestionDTO(Question question);

    Question toQuestionEntity(QuestionDTO questionDTO);

    // Choice 엔티티와 DTO 변환
    ChoiceDTO toChoiceDTO(Choice choice);

    Choice toChoiceEntity(ChoiceDTO choiceDTO);

    // Style 엔티티와 DTO 변환
    StyleDTO toStyleDTO(Style style);

    Style toStyleEntity(StyleDTO styleDTO);

    // 리스트 변환
    List<TestDTO> toTestDTOList(List<Test> testList);

    List<Test> toTestEntityList(List<TestDTO> testDTOList);

    List<QuestionDTO> toQuestionDTOList(List<Question> questionList);

    List<Question> toQuestionEntityList(List<QuestionDTO> questionDTOList);

    List<ChoiceDTO> toChoiceDTOList(List<Choice> choiceList);

    List<Choice> toChoiceEntityList(List<ChoiceDTO> choiceDTOList);

    List<StyleDTO> toStyleDTOList(List<Style> styleList);

    List<Style> toStyleEntityList(List<StyleDTO> styleDTOList);
}
