package com.creativeIdeas.analytics.service;

import com.creativeIdeas.analytics.dto.AnalyticsResponseDto;
import com.creativeIdeas.analytics.repository.CustomAnalyticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {
    private final CustomAnalyticsRepository customAnalyticsRepository;

    public AnalyticsService(CustomAnalyticsRepository customAnalyticsRepository){
        this.customAnalyticsRepository = customAnalyticsRepository;
    }

    public AnalyticsResponseDto getDashboardAnalytics(int lastNDays){
        AnalyticsResponseDto analyticsResponseDto = new AnalyticsResponseDto();
        analyticsResponseDto.setPopularTags(customAnalyticsRepository.findPopularTags());
        analyticsResponseDto.setTopContributors(customAnalyticsRepository.findTopContributors());
        analyticsResponseDto.setTopLikedIdeas(customAnalyticsRepository.getTopLikedIdeas());

        analyticsResponseDto.setDomainDistributors(customAnalyticsRepository.getDomainDistribution());

        List<Map<String, Object>> rawTrends = customAnalyticsRepository.getDailyIdeaTrend(lastNDays);
        List<AnalyticsResponseDto.DailyIdeaStat> trends = rawTrends.stream().map(
                row -> {
                    AnalyticsResponseDto.DailyIdeaStat stat = new AnalyticsResponseDto.DailyIdeaStat();
                    stat.setDate((String) row.get("date"));
                    stat.setIdeaCount((Long) row.get("count"));
                    return stat;
                }
        ).collect(Collectors.toList());

        analyticsResponseDto.setIdeaTrend(trends);
        return analyticsResponseDto;
    }

}
