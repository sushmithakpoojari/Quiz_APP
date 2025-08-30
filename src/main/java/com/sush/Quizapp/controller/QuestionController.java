package com.sush.Quizapp.controller;


import com.sush.Quizapp.entity.Question;
import com.sush.Quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allquestions")
    public List<Question> getAllquestion() {
        return questionService.getAllQuestions();
    }
    @GetMapping("category/{cat}")
    public List<Question> getQuestionByCategory(@PathVariable  String cat){
        return questionService.getQuestionByCategory(cat);
    }
    @GetMapping("options/{op1}")
    public List<Question> getOptions(@PathVariable  String op1){
        return questionService.getOptions(op1);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question question ){
        return questionService.addQuestion(question);
    }

    @PutMapping("update/{id}")
    public String updateQuestion(@PathVariable Integer id,@RequestBody String question){
        return questionService.updateQuestion(id,question);
    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }
    @PutMapping("updatecat/{id}")
    public String updateCat(@PathVariable Integer id,@RequestBody String cat){
        return questionService.updateCat(id,cat);
    }
/************** Exceptions Handling-API Errors*****************************/
    @GetMapping("allquestionscheck")
    public ResponseEntity<List<Question>> getAllquestions() {
    return questionService.getAllQuestionss();
    }

 /*******HTML its failed need to check later*/
    @GetMapping("all")
    public String getAllQuestionshtml(Model model) {
        List<Question> questions = questionService.getAllQuestionshtml();
        model.addAttribute("questions",questions );
        //model.addAttribute("theDate", java.time.LocalDateTime.now());
        return "hello";
 }
}