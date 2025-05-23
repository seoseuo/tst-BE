package com.tst.controller;

import com.tst.dto.ChoiceDTO;
import com.tst.dto.ShowQuestionDTO;
import com.tst.dto.StyleDTO;
import com.tst.dto.TestDTO;

import com.tst.service.TestService;
import com.tst.util.SelectUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestService testService;

    // 테스트 목록 조회
    @GetMapping()
    public ResponseEntity<List<TestDTO>> getTests() {
        log.info("테스트 목록 조회 요청 받음");
        return ResponseEntity.ok(testService.getTests());
    }

    // 테스트 상세 정보 조회
    @GetMapping("/{testId}")
    public ResponseEntity<TestDTO> getTest(@PathVariable int testId) {
        log.info("테스트 상세 정보 조회 요청 받음, testId: {}", testId);
        return ResponseEntity.ok(testService.getTest(testId));
    }

    // 테스트 시작 요청
    @PostMapping("/{testId}/start")
    public ResponseEntity<SelectUtil> startTest(@PathVariable int testId, @RequestParam String userCode) {
        log.info("테스트 시작 요청 받음, testId: {}, userCode: {}", testId, userCode);
        return ResponseEntity.ok(testService.startTest(testId, userCode));
    }

    // 질문 목록 조회
    @GetMapping("/{testId}/questions")
    public ResponseEntity<ShowQuestionDTO> getQuestions(
            @PathVariable int testId,
            @RequestParam String userCode,
            @RequestParam int page) {
        log.info("질문 목록 조회 요청 받음, testId: {}, userCode: {}, page: {}", testId, userCode, page);
        return ResponseEntity.ok(testService.getQuestions(testId, userCode, page));
    }

    // 선택지 선택
    @PostMapping("/{testId}/questions/select")
    public ResponseEntity<Void> selectChoice(
            @PathVariable int testId,
            @RequestBody SelectUtil selectUtil) {
        log.info("선택지 선택 요청 받음, testId: {}, 선택 정보: {}", testId, selectUtil);
        testService.selectChoice(testId, selectUtil);
        return ResponseEntity.ok().build();
    }

    // 결과 가져오기
    @GetMapping("/{testId}/style")
    public ResponseEntity<StyleDTO> getResult(@PathVariable int testId, @RequestParam String userCode) {
        log.info("결과 가져오기 요청 받음, testId: {}, userCode: {}", testId, userCode);

        return ResponseEntity.ok(testService.getStyle(testId, userCode));
    }
}