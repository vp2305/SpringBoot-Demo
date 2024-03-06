package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.QuestionWrapper;
import com.model.Response;
import com.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {

  @Autowired
  QuizService quizService;

  @PostMapping("create")
  public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam Integer numOfQuestions,
      @RequestParam String title) {
    return quizService.createQuiz(category, numOfQuestions, title);
  }

  @GetMapping("get/{id}")
  public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Integer id) {
    return quizService.getQuizById(id);
  }

  @PostMapping("submit/{quizId}")
  public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizId, @RequestBody List<Response> answers) {
    return quizService.calculateQuiz(quizId, answers);
  }
}
