package com.tst.service;

import com.tst.dto.ShowQuestionDTO;
import com.tst.entity.Style;
import com.tst.mapper.EntityDtoMapper;
import com.tst.repository.ChoiceRepository;
import com.tst.repository.QuestionRepository;
import com.tst.repository.StyleRepository;
import com.tst.repository.TestRepository;

import com.tst.util.AnswerSession;
import com.tst.util.SelectedBox;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestServiceTest {

    @Autowired
    TestRepository testRepository;

    @Autowired
    EntityDtoMapper entityDtoMapper;

    @Autowired
    RedisService redisService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ChoiceRepository choiceRepository;

    @Autowired
    StyleRepository styleRepository;

    @Test
    void getTests() {
        log.info("테스트 데이터 조회: {}", entityDtoMapper.toTestDTOList(testRepository.findByIsDelete(1)));
    }

    @Test
    void getTest() {
        // 선택된 testId
        int testId = 1;
        log.info("테스트 데이터 조회: {}", entityDtoMapper.toTestDTO(testRepository.findBytestIdAndIsDelete(testId, 1)));
    }

    @Test
    void startTest() {
        // 선택된 testId
        int testId = 1;
        // 시작하는 userCode
        String userCode = "서승";

        //UUID 생성
        UUID uuid = UUID.randomUUID();
        userCode = userCode + uuid.toString();

        // AnswerSession 객체 생성
        AnswerSession answerSession = new AnswerSession();
        answerSession.setTestId(testId);
        answerSession.setUserCode(userCode);

        redisService.setAnswerSession(userCode, answerSession, 1800000);

        // 전체 문항과 선택지를 담는 DTO 생성
        ShowQuestionDTO boxShowQuestionDTO = new ShowQuestionDTO();

        // 해당 테스트 ID에 대한 모든 질문을 DTO로 변환해 저장
        boxShowQuestionDTO.setQuestions(entityDtoMapper.toQuestionDTOList(questionRepository.findBytestId(testId)));

        // 해당 테스트 ID에 대한 모든 선택지를 DTO로 변환해 저장
        boxShowQuestionDTO.setChoices(entityDtoMapper.toChoiceDTOList(choiceRepository.findBytestId(testId)));

        // 로깅: 해당 테스트의 전체 문항과 선택지 출력
        //log.info(testId + "의 문항들 {}", boxShowQuestionDTO.getQuestions());
        //log.info(testId + "의 선택지들 {}", boxShowQuestionDTO.getChoices());

        // 위에서 구성한 전체 문항/선택지 DTO를 Redis에 저장 (key는 userCode + "questions", 30분 유효)
        redisService.setShowQuestionDTO(userCode + "questions", boxShowQuestionDTO, 1800000);

        log.info("레디스 저장된 데이터 조회: {}", redisService.getAnswerSession(userCode));
        log.info("레디스 저장된 데이터 조회: {}", redisService.getShowQuestionDTO(userCode + "questions"));
    }

    @Test
    void getQuestion() {
        // 선택된 테스트 ID
        int testId = 1;

        // 프론트에서 생성한 사용자 고유 코드 (UUID 포함)
        String userCode = "서승2964b634-ce39-4dcd-8683-b90214332ba1";

        // 현재 페이지 번호 (1페이지부터 시작)
        int page = 4;


        // Redis에 저장한 boxShowQuestionDTO 객체 꺼내기

        ShowQuestionDTO boxShowQuestionDTO = redisService.getShowQuestionDTO(userCode + "questions");

        // 실제로 클라이언트에게 응답할 1개의 질문 + 4개의 선택지를 담을 DTO 객체
        ShowQuestionDTO toShowQuestionDTO = new ShowQuestionDTO();

        // 현재 페이지에 해당하는 선택지 인덱스 계산 (4개씩 보여줌)
        int fromIndex = (page - 1) * 4;
        int toIndex = Math.min(fromIndex + 4, boxShowQuestionDTO.getChoices().size());

        // 현재 페이지에 해당하는 질문 1개 설정 (리스트는 0부터 시작이므로 page - 1)
        toShowQuestionDTO.setShowQuestion((boxShowQuestionDTO.getQuestions().get(page - 1)));

        // 현재 페이지에 해당하는 선택지 4개 설정 (범위: fromIndex ~ toIndex - 1)
        toShowQuestionDTO.setChoices(boxShowQuestionDTO.getChoices().subList(fromIndex, toIndex));

        // 현재 페이지의 질문과 선택지 로그 출력
        log.info(testId + "의 " + page + "문항 {}", toShowQuestionDTO.getShowQuestion());
        log.info(page + "문항 선택지 {}", toShowQuestionDTO.getChoices());
    }

    @Test
    void selectChoice() {
        int questionId = 3;
        int styleId1 = 2;
        int styleId2 = 5;
        String userCode = "서승2964b634-ce39-4dcd-8683-b90214332ba1";


        SelectedBox selectBox = new SelectedBox();
        selectBox.setStyleId1(styleId1);
        selectBox.setStyleId2(styleId2);

        AnswerSession answerSession = redisService.getAnswerSession(userCode);

        answerSession.getSelectedBoxesMap().put(questionId,selectBox);

        redisService.setAnswerSession(userCode, answerSession, 1800000);

        log.info("레디스 저장된 데이터 조회: {}", redisService.getAnswerSession(userCode));

    }

    @Test
    void getStyle() {
        int testId = 1;
        String userCode = "서승2964b634-ce39-4dcd-8683-b90214332ba1";
        AnswerSession answerSession = redisService.getAnswerSession(userCode);

        Optional<Style> optionalStyle = styleRepository.findById(answerSession.findMostSelectedStyle());
        Style style = optionalStyle.orElseThrow(() -> new RuntimeException("Style not found"));

        log.info("당신의 유형은 "+answerSession.findMostSelectedStyle()+" {} 입니다.",entityDtoMapper.toStyleDTO(style));
    }
}
