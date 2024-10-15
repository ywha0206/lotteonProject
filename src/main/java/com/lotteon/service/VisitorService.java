package com.lotteon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class VisitorService {
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    // 방문자 수 증가 메서드
    public void incrementVisitorCount(String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.increment(key);

    }

    // 방문자 수 조회 메서드
    public Long getVisitorCount(String key) {
        Object count = redisTemplate.opsForValue().get(key);
        return count != null ? Long.parseLong(String.valueOf(count)) : 0L;
    }

    // 특정 키에 TTL 설정 (예: 하루 동안 유지)
    public void setKeyExpiration(String key, long ttl) {
        redisTemplate.expire(key, Duration.ofSeconds(ttl));
    }

}
