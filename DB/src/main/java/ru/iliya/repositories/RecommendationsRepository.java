package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Book;
import ru.iliya.entities.Recommendations;
import ru.iliya.entities.User;

import java.util.List;

@Repository
public interface RecommendationsRepository extends JpaRepository<Recommendations, Integer> {
    List<Recommendations> findByUser(User user);
    void deleteRecommendationsByRecommendationID(int recommendationID);
    Recommendations findRecommendationsByBookAndUser(Book book, User user);
    Recommendations findRecommendationByRecommendationID (int recommendationId);
}













