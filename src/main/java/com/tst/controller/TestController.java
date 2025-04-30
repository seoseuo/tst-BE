package com.tst.controller;

import com.tst.dto.TestDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
public class TestController {

    @GetMapping()
    public ResponseEntity<TestDTO> getTests() {
        // log.info("컨트롤러 : 테스트 목록 가져오기(getTests)");
        return ResponseEntity.ok(new TestDTO());
    }
}