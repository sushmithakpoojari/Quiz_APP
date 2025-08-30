package com.sush.Quizapp.Dao;

import com.sush.Quizapp.entity.Question;
import com.sush.Quizapp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz,Integer> {


}
