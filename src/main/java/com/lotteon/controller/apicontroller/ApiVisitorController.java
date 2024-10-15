package com.lotteon.controller.apicontroller;

import com.lotteon.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ApiVisitorController {
    private final VisitorService visitorService;

    @PostMapping("/track-visitor")
    public ResponseEntity<String> trackVisitor() {
        String key = "visitor:count:" + LocalDate.now();  // 매일 다른 키를 사용
        visitorService.incrementVisitorCount(key);

        // TTL을 하루로 설정 (24시간)
        visitorService.setKeyExpiration(key, 86400);

        return ResponseEntity.ok("Visitor count incremented.");
    }

    // 현재 방문자 수 조회
    @GetMapping("/visitor-count")
    public ResponseEntity<Long> getVisitorCount() {
        String key = "visitor:count:" + LocalDate.now();
        Long count = visitorService.getVisitorCount(key);
        return ResponseEntity.ok(count);
    }
}


