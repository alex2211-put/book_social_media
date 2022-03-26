package ru.iliya.services;

import ru.iliya.entities.Recommendations;

import java.util.List;

public interface RecommendationsService {
    void setRecommendationsByParams(int userID, int bookID);
    List<Recommendations> findRecommendationsByUserID(int userID);
    void deleteRecommendationsByRecommendationsID(int recommendationsID);
}
