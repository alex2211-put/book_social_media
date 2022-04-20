package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Book;
import ru.iliya.entities.Recommendations;
import ru.iliya.entities.User;
import ru.iliya.repositories.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class RecommendationsBaseServiceImpl implements RecommendationsBaseService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecommendationsRepository recommendationsRepository;

    @Override
    public void setRecommendationsByParams(int userID, int bookID) {
        Recommendations recommendations = new Recommendations();
        recommendations.setBook(bookRepository.findBookByBookID(bookID));
        recommendations.setUser(userRepository.findByUserID(userID));
        Date current = new Date();
        recommendations.setDateRecommendation(current);
        recommendationsRepository.save(recommendations);
    }

    @Override
    public List<Recommendations> findRecommendationsByUserID(int userID) {
        return recommendationsRepository.findByUser(userRepository.findByUserID(userID));
    }

    @Override
    @Transactional
    public void deleteRecommendationsByRecommendationsID(int recommendationsID) {
        recommendationsRepository.deleteRecommendationsByRecommendationID(recommendationsID);
    }

    @Override
    public Recommendations findRecommendationByUserIdAndBookId(int userId, int bookId) {
        User user = userRepository.findByUserID(userId);
        Book book = bookRepository.findBookByBookID(bookId);
        return recommendationsRepository.findRecommendationsByBookAndUser(book, user);
    }

    @Override
    public Recommendations findRecommendationByRecommendationId(int recommendationId) {
        return recommendationsRepository.findRecommendationByRecommendationID(recommendationId);
    }

}
