package com.tst.service;

import com.tst.dto.ShowQuestionDTO;
import com.tst.dto.SelectedChoiceDTO;
import com.tst.dto.TestDTO;
import com.tst.entity.Style;
import com.tst.mapper.EntityDtoMapper;
import com.tst.repository.ChoiceRepository;
import com.tst.repository.QuestionRepository;
import com.tst.repository.StyleRepository;
import com.tst.repository.TestRepository;
import com.tst.util.AnswerSession;
import com.tst.util.SelectedBox;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

    @Autowired
    private StyleRepository styleRepository;

    public List<TestDTO> getTests() {
        log.info("전체 테스트 데이터 조회");
        return entityDtoMapper.toTestDTOList(testRepository.findByIsDelete(1));
    }

    public TestDTO getTest(int testId) {
        log.info("testId: {} 에 대한 테스트 상세 조회", testId);
        return entityDtoMapper.toTestDTO(testRepository.findBytestIdAndIsDelete(testId, 1));
    }

    public void startTest(int testId, String userCode) {
        log.info("testId: {}, userCode: {}로 테스트 시작", testId, userCode);
        // UUID 생성
        userCode = userCode + UUID.randomUUID().toString();

        // 답변세선 세팅
        AnswerSession answerSession = new AnswerSession();
        answerSession.setTestId(testId);
        answerSession.setUserCode(userCode);

        redisService.setAnswerSession(userCode, answerSession, 1800000);

        // 문항 세팅
        ShowQuestionDTO boxShowQuestionDTO = new ShowQuestionDTO();
        boxShowQuestionDTO.setQuestions(entityDtoMapper.toQuestionDTOList(questionRepository.findBytestId(testId)));
        boxShowQuestionDTO.setChoices(entityDtoMapper.toChoiceDTOList(choiceRepository.findBytestId(testId)));

        redisService.setShowQuestionDTO(userCode + "questions", boxShowQuestionDTO, 1800000);

        log.info("레디스에 저장된 데이터 조회: {}", redisService.getAnswerSession(userCode));
        log.info("레디스에 저장된 문항과 선택지: {}", redisService.getShowQuestionDTO(userCode + "questions"));
    }

    public void getQuestions(int testId, String userCode, int page) {
        log.info("testId: {}, userCode: {}, page: {}로 질문 조회", testId, userCode, page);
        ShowQuestionDTO boxShowQuestionDTO = redisService.getShowQuestionDTO(userCode + "questions");

        ShowQuestionDTO toShowQuestionDTO = new ShowQuestionDTO();
        int fromIndex = (page - 1) * 4;
        int toIndex = Math.min(fromIndex + 4, boxShowQuestionDTO.getChoices().size());

        toShowQuestionDTO.setShowQuestion(boxShowQuestionDTO.getQuestions().get(page - 1));
        toShowQuestionDTO.setChoices(boxShowQuestionDTO.getChoices().subList(fromIndex, toIndex));

        log.info("testId: {}, page: {}의 문항과 선택지: {}, {}", testId, page, toShowQuestionDTO.getShowQuestion(), toShowQuestionDTO.getChoices());
    }

    public void selectChoice(int testId, int questionId, int styleId1, int styleId2, String userCode) {
        log.info("testId: {}, questionId: {}, styleId1: {}, styleId2: {}로 선택지 저장", testId, questionId, styleId1, styleId2);

        SelectedBox selectedBox = new SelectedBox();
        selectedBox.setStyleId1(styleId1);
        selectedBox.setStyleId2(styleId2);

        AnswerSession answerSession = redisService.getAnswerSession(userCode);
        answerSession.getSelectedBoxesMap().put(questionId, selectedBox);
        redisService.setAnswerSession(userCode, answerSession, 1800000);

        log.info("레디스에 저장된 선택지: {}", redisService.getAnswerSession(userCode));
    }

    public void getTestResult(int testId, String userCode) {
        log.info("testId: {}, userCode: {}로 결과 조회", testId, userCode);

        AnswerSession answerSession = redisService.getAnswerSession(userCode);
        Optional<Style> optionalStyle = styleRepository.findById(answerSession.findMostSelectedStyle());
        Style style = optionalStyle.orElseThrow(() -> new RuntimeException("Style not found"));

        log.info("testId: {}, userCode: {}의 결과: {} - {}", testId, userCode, answerSession.findMostSelectedStyle(), entityDtoMapper.toStyleDTO(style));
    }
}
