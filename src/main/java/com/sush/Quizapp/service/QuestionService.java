package com.sush.Quizapp.service;

import com.sush.Quizapp.Dao.QuestionDao;
import com.sush.Quizapp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionByCategory(String cat) {
        return questionDao.findByCategory(cat);
    }
    public List<Question> getOptions(String op1) {
        return questionDao.findByOption1(op1);
    }


    public String addQuestion(Question question) {
         questionDao.save(question);
         return("success");
    }

    public String updateQuestion(Integer id, String question) {
        Optional<Question> daoupdate = questionDao.findById(id);
        Question existingQuestion = daoupdate.get();
        existingQuestion.setQuestiontitle(question);
        questionDao.save(existingQuestion);
        return(daoupdate + "Update successfully");
    }

    public String deleteQuestion(Integer id) {
        Optional<Question> daodelete= questionDao.findById(id);
        Question deleteQuestion = daodelete.get();
        questionDao.delete(deleteQuestion);
        return(id + "Deleted Successfully");
    }

    //Customized Query
    public String updateCat(Integer id, String cat) {
        int updatedRows = questionDao.updateCat(id,cat);

        if (updatedRows > 0) {
            return "Update successful";
        } else {
            return "No record found to update";
        }

    }

    public ResponseEntity<List<Question>> getAllQuestionss() {
        try{
            //return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
            return ResponseEntity.ok().body(questionDao.findAll());
           }
        catch(Exception e){ e.printStackTrace();}
        //return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(new ArrayList<>());
    }

    public List<Question> getAllQuestionshtml() {
        return questionDao.findAll();
    }
}
