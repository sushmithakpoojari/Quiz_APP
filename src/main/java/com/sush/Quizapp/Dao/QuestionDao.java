package com.sush.Quizapp.Dao;

import com.sush.Quizapp.entity.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String cat);
    List<Question> findByOption1(String op1);


    @Modifying
    @Transactional
    @Query("UPDATE Question q SET q.category = :cat WHERE q.id = :id")
    int updateCat(@Param("id") Integer id, @Param("cat") String questionTitle);

    @Query(value = "SELECT * FROM Question q Where q.category = :cat ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String cat, int numQ, String title);
}
