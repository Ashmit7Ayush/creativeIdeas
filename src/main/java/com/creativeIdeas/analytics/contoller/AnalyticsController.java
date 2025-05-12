package com.creativeIdeas.analytics.contoller;

import com.creativeIdeas.analytics.dto.AnalyticsResponseDto;
import com.creativeIdeas.analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping
    public ResponseEntity<AnalyticsResponseDto> getAnalytics(@RequestParam(defaultValue = "7") int days){
        AnalyticsResponseDto dto = analyticsService.getDashboardAnalytics(days);
        return ResponseEntity.ok(dto);
    }
}
