package ru.iliya.services;

import ru.iliya.entities.Recommendations;

import java.util.List;

public interface RecommendationsBaseService {

    void setRecommendationsByParams(int userID, int bookID);

    List<Recommendations> findRecommendationsByUserID(int userID);

    void deleteRecommendationsByRecommendationsID(int recommendationsID);

    Recommendations findRecommendationByUserIdAndBookId(int userId, int bookId);

    Recommendations findRecommendationByRecommendationId(int recommendationId);
}
