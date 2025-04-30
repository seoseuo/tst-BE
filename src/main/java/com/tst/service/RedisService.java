package com.tst.service;

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

    public void set(String key, AnswerSession answerSession, int seconds){
        ValueOperations<String, Object> valOperations = redisTemplate.opsForValue();
        valOperations.set(key,answerSession,seconds, TimeUnit.SECONDS);
    }

    public AnswerSession get(String key) {
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
}
