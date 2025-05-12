package com.creativeIdeas.analytics.constants;

public class AnalyticsConstants {
    public static final String FIND_TOP_CONTRIBUTORS = "SELECT i.author.username FROM Idea i GROUP BY i.author.username" +
            "ORDER BY COUNT(i.id) DESC";

    public static final String FIND_POPULAR_TAGS = "SELECT tag from idea_tags GROUP BY tag ORDER BY COUNT(*) DESC LIMIT 5";

    public static final String DOMAIN_DISTRIBUTION = "SELECT i.domain, COUNT(i.id) FROM Idea i GROUP BY i.domain";

    public static final String DAILY_IDEA_TREND = "SELECT DATE(created_at) as date, COUNT(*) as count FROM Idea " +
            "WHERE created_at >= CURDATE() - INTERVAL :days DAY GROUP BY DATE(created_at) ORDER BY date";

    public static final String TOP_LIKED_IDEAS = "SELECT i.title FROM Idea i ORDER BY i.popularityScore DESC";
}
