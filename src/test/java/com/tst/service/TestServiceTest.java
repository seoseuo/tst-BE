package com.tst.service;

import com.tst.mapper.EntityDtoMapper;
import com.tst.repository.TestRepository;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestServiceTest {    

    @Autowired
    TestRepository testRepository;

    @Autowired
    EntityDtoMapper entityDtoMapper;

    @Test
    void getTests() {
        log.info("테스트 데이터 조회: {}", entityDtoMapper.toTestDTOList(testRepository.findByIsDelete(1)));
    }

    @Test
    void getTest() {
        // 선택된 testId
        int testId = 1;
        log.info("테스트 데이터 조회: {}", entityDtoMapper.toTestDTO(testRepository.findBytestIdAndIsDelete(testId,1)));
    }

    @Test
    void startTest() {
        // 선택된 testId
        int testId = 1;
        // 시작하는 userCode
        String userCode = "서승";

        //UUID 생성
        UUID uuid = UUID.randomUUID();
        String uuidString = userCode+uuid.toString();

        Redis


        log.info("테스트 데이터 조회: {}", entityDtoMapper.toTestDTO(testRepository.findBytestIdAndIsDelete(testId,1)));
    }
}
