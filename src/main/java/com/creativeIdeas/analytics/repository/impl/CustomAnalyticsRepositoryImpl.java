package com.creativeIdeas.analytics.repository.impl;

import com.creativeIdeas.analytics.repository.CustomAnalyticsRepository;
import com.creativeIdeas.analytics.constants.AnalyticsConstants;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomAnalyticsRepositoryImpl implements CustomAnalyticsRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<String> findTopContributors(){
        String query = AnalyticsConstants.FIND_TOP_CONTRIBUTORS;
        return em.createQuery(query, String.class).setMaxResults(5).getResultList();
    }

    @Override
    public List<String> findPopularTags(){
        String sql = AnalyticsConstants.FIND_POPULAR_TAGS;
        Query query = em.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    public Map<String, Long> getDomainDistribution(){
        String sql = AnalyticsConstants.DOMAIN_DISTRIBUTION;
        List<Object[]> results = em.createQuery(sql).getResultList();

        Map<String, Long> map = new HashMap<>();
        for(Object[] row : results){
            map.put((String) row[0], (Long) row[1]);
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> getDailyIdeaTrend(int lastNDays){
        String sql = AnalyticsConstants.DAILY_IDEA_TREND;
        Query query = em.createNativeQuery(sql);
        query.setParameter("days", lastNDays);

        List<Object[]> results = query.getResultList();
        List<Map<String, Object>> list = new ArrayList<>();
        for(Object[] row : results){
            Map<String, Object> item = new HashMap<>();
            item.put("date", row[0].toString());
            item.put("count", ((Number) row[1]).longValue());
            list.add(item);
        }
        return list;
    }

    @Override
    public List<String> getTopLikedIdeas(){
        String sql = AnalyticsConstants.TOP_LIKED_IDEAS;
        return em.createQuery(sql, String.class).setMaxResults(5).getResultList();
    }

}
