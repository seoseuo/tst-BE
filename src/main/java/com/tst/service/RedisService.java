package com.tst.service;

import com.tst.dto.ShowQuestionDTO;
import com.tst.util.AnswerSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void setAnswerSession(String key, AnswerSession answerSession, int seconds){
        ValueOperations<String, Object> valOperations = redisTemplate.opsForValue();
        valOperations.set(key,answerSession,seconds, TimeUnit.SECONDS);
    }

    public AnswerSession getAnswerSession(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("키 값이 비어있습니다");
        }

        ValueOperations<String, Object> valOperations = redisTemplate.opsForValue();
        Object value = valOperations.get(key);

        if (value == null) {
            return null;
        }

        if (!(value instanceof AnswerSession)) {
            throw new IllegalStateException("Redis에서 가져온 값이 AnswerSession이 아닙니다");
        }

        return (AnswerSession) value;
    }

    public void delete(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("삭제할 키가 비어있습니다");
        }

        Boolean deleted = redisTemplate.delete(key);
        if (Boolean.TRUE.equals(deleted)) {
            log.info("Redis에서 키 [{}]에 해당하는 값을 삭제했습니다", key);
        } else {
            log.warn("Redis에서 키 [{}] 삭제 실패 또는 존재하지 않음", key);
        }
    }

    public void setShowQuestionDTO(String key, ShowQuestionDTO showQuestionDTO, int seconds){
        ValueOperations<String, Object> valOperations = redisTemplate.opsForValue();
        valOperations.set(key,showQuestionDTO,seconds, TimeUnit.SECONDS);
    }

    public ShowQuestionDTO getShowQuestionDTO(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("키 값이 비어있습니다");
        }

        ValueOperations<String, Object> valOperations = redisTemplate.opsForValue();
        Object value = valOperations.get(key);

        if (value == null) {
            return null;
        }

        if (!(value instanceof ShowQuestionDTO)) {
            throw new IllegalStateException("Redis에서 가져온 값이 ShowQuestionDTO가 아닙니다");
        }

        return (ShowQuestionDTO) value;
    }

}
