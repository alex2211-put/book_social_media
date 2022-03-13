package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Favourites;
import ru.iliya.entities.Recommendations;

import java.util.List;

@Repository
public interface RecommendationsRepository extends JpaRepository<Recommendations, Integer> {
    List<Recommendations> findByUserID(int userID);
    void deleteRecommendationsByRecommendationID(int recommendationID);
}













