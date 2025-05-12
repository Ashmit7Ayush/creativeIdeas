package com.creativeIdeas.analytics.repository;

import java.util.List;
import java.util.Map;

public interface CustomAnalyticsRepository {
    List<String> findTopContributors();
    List<String> findPopularTags();
    Map<String, Long> getDomainDistribution();
    List<Map<String, Object>> getDailyIdeaTrend(int lastNDays);
    List<String> getTopLikedIdeas();
}
