package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Recommendations;

import java.util.List;

/// TODO test all methods
@Service
public class RecommendationsServiceImpl implements RecommendationsService {

    @Autowired
    RecommendationsDataBaseService recommendationsDataBaseService;

    @Override
    public void setRecommendationsByParams(int userID, int bookID) {
        recommendationsDataBaseService.setRecommendationsByParams(userID, bookID);
    }

    @Override
    public List<Recommendations> findRecommendationsByUserID(int userID) {
        return recommendationsDataBaseService.findRecommendationsByUserID(userID);
    }

    @Override
    public void deleteRecommendationsByRecommendationsID(int recommendationsID) {
        recommendationsDataBaseService.deleteRecommendationsByRecommendationsID(recommendationsID);
    }

    @Override
    public Recommendations findRecommendationByRecommendationId(int recommendationId) {
        return recommendationsDataBaseService.findRecommendationByRecommendationId(recommendationId);
    }

    @Override
    public Recommendations findRecommendationByUserIdAndBookId(int userId, int bookId) {
        return recommendationsDataBaseService.findRecommendationByUserIdAndBookId(userId, bookId);
    }
}
