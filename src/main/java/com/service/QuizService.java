package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.QuestionDao;
import com.dao.QuizDao;
import com.model.Quiz;
import com.model.Response;
import com.model.Question;
import com.model.QuestionWrapper;

@Service
public class QuizService {

  @Autowired
  QuizDao quizDao;
  @Autowired
  QuestionDao questionDao;

  public ResponseEntity<String> createQuiz(String category, int numberOfQuestions, String title) {
    List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numberOfQuestions);

    Quiz quiz = new Quiz();
    quiz.setTitle(title);
    quiz.setQuestions(questions);

    try {
      quizDao.save(quiz);
      return new ResponseEntity<String>("Quiz created successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<String>("Failed to create quiz", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {
    try {
      Quiz quiz = quizDao.findById(id).orElse(null);
      if (quiz == null) {
        return new ResponseEntity<List<QuestionWrapper>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
      }

      List<Question> questionsFromDb = quiz.getQuestions();
      List<QuestionWrapper> questions = new ArrayList<>();

      for (Question question : questionsFromDb) {
        QuestionWrapper questionWrapper = new QuestionWrapper(
            question.getId(), question.getQuestiontitle(), question.getCategory(),
            question.getOption1(), question.getOption2(), question.getOption3());
        questions.add(questionWrapper);
      }

      return new ResponseEntity<List<QuestionWrapper>>(questions, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<List<QuestionWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Integer> calculateQuiz(Integer quizId, List<Response> answers) {
    try {
      Quiz quiz = quizDao.findById(quizId).orElse(null);
      if (quiz == null) {
        System.out.println("Quiz not found");
        return new ResponseEntity<Integer>(-1, HttpStatus.NOT_FOUND);
      }

      List<Question> questions = quiz.getQuestions();
      System.out.println(questions);
      int right = 0;
      int idx = 0;
      for (Response response : answers) {
        if (response.getAnswer().equals(questions.get(idx).getRightanswer())) {
          right++;
        }
        idx++;
      }

      return new ResponseEntity<Integer>(right, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Integer>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
