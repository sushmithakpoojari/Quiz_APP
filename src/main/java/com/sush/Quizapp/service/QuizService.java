package com.sush.Quizapp.service;

import com.sush.Quizapp.Dao.QuestionDao;
import com.sush.Quizapp.Dao.QuizDao;
import com.sush.Quizapp.entity.Question;
import com.sush.Quizapp.entity.QuestionWrapper;
import com.sush.Quizapp.entity.Quiz;
import com.sush.Quizapp.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizdao;
    @Autowired
    QuestionDao questiondao;
    public ResponseEntity<String> createQuiz(String cat, int numQ, String title) {
        List<Question> questions = questiondao.findRandomQuestionByCategory(cat,numQ,title);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizdao.save(quiz);
        return new ResponseEntity<>("Saved", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizdao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUSer = new ArrayList<>();
        for(Question q : questionFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),
                    q.getQuestiontitle(),q.getOption1(),q.getAnswer());
            questionForUSer.add(qw);
        }
        return new ResponseEntity<>(questionForUSer,HttpStatus.OK) ;
    }

    public ResponseEntity<?> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizdao.findById(id);
        if (quiz.isEmpty()) {
            return new ResponseEntity<>("Quiz not found with ID: " + id, HttpStatus.NOT_FOUND);
        }

        List<Question> questions = quiz.get().getQuestions();
        int right = 0;
        int i =0;
        for(Response response : responses)
        {
            if(response.getResponse().equals(questions.get(i).getAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
