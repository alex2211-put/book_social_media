package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Recommendations;

import java.util.List;

@Service
public class RecommendationsServiceImpl implements RecommendationsService {

    @Autowired
    RecommendationsBaseService recommendationsBaseService;

    @Override
    public void setRecommendationsByParams(int userID, int bookID) {
        recommendationsBaseService.setRecommendationsByParams(userID, bookID);
    }

    @Override
    public List<Recommendations> findRecommendationsByUserID(int userID) {
        return recommendationsBaseService.findRecommendationsByUserID(userID);
    }

    @Override
    public void deleteRecommendationsByRecommendationsID(int recommendationsID) {
        recommendationsBaseService.deleteRecommendationsByRecommendationsID(recommendationsID);
    }

    @Override
    public Recommendations findRecommendationByRecommendationId(int recommendationId) {
        return recommendationsBaseService.findRecommendationByRecommendationId(recommendationId);
    }

    @Override
    public Recommendations findRecommendationByUserIdAndBookId(int userId, int bookId) {
        return recommendationsBaseService.findRecommendationByUserIdAndBookId(userId, bookId);
    }
}
