package org.example.repository;

import org.example.model.LeaderboardDTO;
import org.example.model.Quiz;
import org.example.model.QuizAttempt;
import org.example.model.User;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Integer> {
    Optional<QuizAttempt> findByUserAndQuiz(User user, Quiz quiz);

    @Query("SELECT new org.example.model.LeaderboardDTO(qa.user.userName, SUM(qa.score), COUNT(qa)) " +
            "FROM QuizAttempt qa GROUP BY qa.user.userName ORDER BY SUM(qa.score) DESC")
    List<LeaderboardDTO> getLeaderboard();
    
    @Query("SELECT AVG(qa.score) FROM QuizAttempt qa WHERE qa.quiz.id = :quizId")
    Double findAverageScoreByQuizId(Integer quizId);

    List<QuizAttempt> findByUser_UserName(String userName);
}
