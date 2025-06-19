package com.tst.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.tst.dto.*;
import com.tst.entity.*;

import java.util.List;

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

    // UserAccount 엔티티와 DTO 변환
    UserAccountDTO toUserAccountDTO(UserAccount userAccount);
    UserAccount toUserAccountEntity(UserAccountDTO userAccountDTO);

    // AdminAccount 엔티티와 DTO 변환
    AdminAccountDTO toAdminAccountDTO(AdminAccount adminAccount);
    AdminAccount toAdminAccountEntity(AdminAccountDTO adminAccountDTO);

    // ChatSession 엔티티와 DTO 변환
//    ChatSessionDTO toChatSessionDTO(ChatSession chatSession);
//    ChatSession toChatSessionEntity(ChatSessionDTO chatSessionDTO);

    // ChatMessage 엔티티와 DTO 변환
//    ChatMessageDTO toChatMessageDTO(ChatMessage chatMessage);
//    ChatMessage toChatMessageEntity(ChatMessageDTO chatMessageDTO);

    // 리스트 변환 - Test
    List<TestDTO> toTestDTOList(List<Test> testList);
    List<Test> toTestEntityList(List<TestDTO> testDTOList);

    // 리스트 변환 - Question
    List<QuestionDTO> toQuestionDTOList(List<Question> questionList);
    List<Question> toQuestionEntityList(List<QuestionDTO> questionDTOList);

    // 리스트 변환 - Choice
    List<ChoiceDTO> toChoiceDTOList(List<Choice> choiceList);
    List<Choice> toChoiceEntityList(List<ChoiceDTO> choiceDTOList);

    // 리스트 변환 - Style
    List<StyleDTO> toStyleDTOList(List<Style> styleList);
    List<Style> toStyleEntityList(List<StyleDTO> styleDTOList);

    // 리스트 변환 - UserAccount
    List<UserAccountDTO> toUserAccountDTOList(List<UserAccount> userAccountList);
    List<UserAccount> toUserAccountEntityList(List<UserAccountDTO> userAccountDTOList);

    // 리스트 변환 - AdminAccount
    List<AdminAccountDTO> toAdminAccountDTOList(List<AdminAccount> adminAccountList);
    List<AdminAccount> toAdminAccountEntityList(List<AdminAccountDTO> adminAccountDTOList);

    // 리스트 변환 - ChatSession
//    List<ChatSessionDTO> toChatSessionDTOList(List<ChatSession> chatSessionList);
//    List<ChatSession> toChatSessionEntityList(List<ChatSessionDTO> chatSessionDTOList);

    // 리스트 변환 - ChatMessage
//    List<ChatMessageDTO> toChatMessageDTOList(List<ChatMessage> chatMessageList);
//    List<ChatMessage> toChatMessageEntityList(List<ChatMessageDTO> chatMessageDTOList);
}