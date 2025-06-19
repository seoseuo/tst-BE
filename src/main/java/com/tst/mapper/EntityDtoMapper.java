package com.tst.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
    @Mapping(source = "test", target = "testDTO")       // test → testDTO 매핑 명시
    QuestionDTO toQuestionDTO(Question question);

    @Mapping(source = "testDTO", target = "test")       // testDTO → test 매핑 명시
    Question toQuestionEntity(QuestionDTO questionDTO);

    // Choice 엔티티와 DTO 변환
    @Mapping(source = "question", target = "questionDTO")   // question → questionDTO 명시
    @Mapping(source = "style", target = "styleDTO")         // style → styleDTO 명시
    ChoiceDTO toChoiceDTO(Choice choice);

    @Mapping(source = "questionDTO", target = "question")   // questionDTO → question 명시
    @Mapping(source = "styleDTO", target = "style")         // styleDTO → style 명시
    Choice toChoiceEntity(ChoiceDTO choiceDTO);

    // Style 엔티티와 DTO 변환
    @Mapping(source = "test", target = "testDTO")       // test → testDTO 매핑 명시
    StyleDTO toStyleDTO(Style style);

    @Mapping(source = "testDTO", target = "test")       // testDTO → test 매핑 명시
    Style toStyleEntity(StyleDTO styleDTO);

    // UserAccount 엔티티와 DTO 변환
    UserAccountDTO toUserAccountDTO(UserAccount userAccount);
    UserAccount toUserAccountEntity(UserAccountDTO userAccountDTO);

    // AdminAccount 엔티티와 DTO 변환
    AdminAccountDTO toAdminAccountDTO(AdminAccount adminAccount);
    AdminAccount toAdminAccountEntity(AdminAccountDTO adminAccountDTO);

    // ChatSession 엔티티와 DTO 변환 (주석 해제 후 필요시 매핑 명시)
    // ChatSessionDTO toChatSessionDTO(ChatSession chatSession);
    // ChatSession toChatSessionEntity(ChatSessionDTO chatSessionDTO);

    // ChatMessage 엔티티와 DTO 변환 (주석 해제 후 필요시 매핑 명시)
    // ChatMessageDTO toChatMessageDTO(ChatMessage chatMessage);
    // ChatMessage toChatMessageEntity(ChatMessageDTO chatMessageDTO);

    // 리스트 변환 - Test
    List<TestDTO> toTestDTOList(List<Test> testList);
    List<Test> toTestEntityList(List<TestDTO> testDTOList);

    // 리스트 변환 - Question
    @Mapping(source = "test", target = "testDTO")
    List<QuestionDTO> toQuestionDTOList(List<Question> questionList);

    @Mapping(source = "testDTO", target = "test")
    List<Question> toQuestionEntityList(List<QuestionDTO> questionDTOList);

    // 리스트 변환 - Choice
    @Mapping(source = "question", target = "questionDTO")
    @Mapping(source = "style", target = "styleDTO")
    List<ChoiceDTO> toChoiceDTOList(List<Choice> choiceList);

    @Mapping(source = "questionDTO", target = "question")
    @Mapping(source = "styleDTO", target = "style")
    List<Choice> toChoiceEntityList(List<ChoiceDTO> choiceDTOList);

    // 리스트 변환 - Style
    @Mapping(source = "test", target = "testDTO")
    List<StyleDTO> toStyleDTOList(List<Style> styleList);

    @Mapping(source = "testDTO", target = "test")
    List<Style> toStyleEntityList(List<StyleDTO> styleDTOList);

    // 리스트 변환 - UserAccount
    List<UserAccountDTO> toUserAccountDTOList(List<UserAccount> userAccountList);
    List<UserAccount> toUserAccountEntityList(List<UserAccountDTO> userAccountDTOList);

    // 리스트 변환 - AdminAccount
    List<AdminAccountDTO> toAdminAccountDTOList(List<AdminAccount> adminAccountList);
    List<AdminAccount> toAdminAccountEntityList(List<AdminAccountDTO> adminAccountDTOList);

    // 리스트 변환 - ChatSession (필요시 매핑 명시)
    // List<ChatSessionDTO> toChatSessionDTOList(List<ChatSession> chatSessionList);
    // List<ChatSession> toChatSessionEntityList(List<ChatSessionDTO> chatSessionDTOList);

    // 리스트 변환 - ChatMessage (필요시 매핑 명시)
    // List<ChatMessageDTO> toChatMessageDTOList(List<ChatMessage> chatMessageList);
    // List<ChatMessage> toChatMessageEntityList(List<ChatMessageDTO> chatMessageDTOList);
}
