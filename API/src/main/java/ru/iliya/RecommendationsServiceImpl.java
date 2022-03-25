package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Recommendations;
import ru.iliya.repositories.BaseRepository;

import java.util.List;

@Service
public class RecommendationsServiceImpl implements RecommendationsService {

    @Autowired
    BaseRepository baseRepository;

    @Override
    public void setRecommendationsByParams(int userID, int bookID) {
        baseRepository.setRecommendationsByParams(userID, bookID);
    }

    @Override
    public List<Recommendations> findRecommendationsByUserID(int userID) {
        return baseRepository.findRecommendationsByUserID(userID);
    }

    @Override
    public void deleteRecommendationsByRecommendationsID(int recommendationsID) {
        baseRepository.deleteRecommendationsByRecommendationsID(recommendationsID);
    }

    @Override
    public Recommendations findRecommendationByRecommendationId(int recommendationId) {
        return baseRepository.findRecommendationByRecommendationId(recommendationId);
    }

    @Override
    public Recommendations findRecommendationByUserIdAndBookId(int userId, int bookId) {
        return baseRepository.findRecommendationByUserIdAndBookId(userId, bookId);
    }
}
