package org.example.repository;

import org.example.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByApprovedTrue();
    List<Quiz> findByApprovedFalse();
    List<Quiz> findByContributorUserId(Integer userId);
}
