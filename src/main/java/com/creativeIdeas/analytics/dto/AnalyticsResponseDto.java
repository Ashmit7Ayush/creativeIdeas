package com.creativeIdeas.analytics.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AnalyticsResponseDto {
    private List<String> topContributors;
    private List<String> popularTags;
    private Map<String, Long> domainDistributors;
    private List<DailyIdeaStat> ideaTrend;
    private List<String> topLikedIdeas;

    @Getter
    @Setter
    public static class DailyIdeaStat{
        private String date;
        private long ideaCount;
    }


}
