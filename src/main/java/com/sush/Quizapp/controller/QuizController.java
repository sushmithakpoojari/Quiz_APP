package com.sush.Quizapp.controller;

import com.sush.Quizapp.entity.QuestionWrapper;
import com.sush.Quizapp.entity.Response;
import com.sush.Quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    //http://localhost:8080/quiz/create?cat=Java&numQ=10&title=Basics
    public ResponseEntity<String> createQuiz(@RequestParam String cat,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(cat,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
        //return new ResponseEntity<>("Something is working", HttpStatus.OK);
    }
}
