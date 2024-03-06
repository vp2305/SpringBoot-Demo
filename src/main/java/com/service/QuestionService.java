package com.service;

import com.dao.QuestionDao;
import com.model.Question;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

  @Autowired
  QuestionDao questionDao;

  public ResponseEntity<List<Question>> getAllQuestions() {
    try {
      return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
  }

  public ResponseEntity<List<Question>> getQuestionsByCategory(String categoryName) {
    try {
      if (categoryName == null || categoryName.isEmpty()) {
        throw new IllegalArgumentException("Category name is required");
      }
      return new ResponseEntity<>(questionDao.findByCategory(categoryName), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
  }

  public ResponseEntity<String> addQuestion(Question question) {
    try {
      questionDao.save(question);
      return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("Error adding question", HttpStatus.BAD_REQUEST);
    }
  }

  public ResponseEntity<String> deleteQuestion(int id) {
    try {
      questionDao.deleteById(id);
      return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error deleting question", HttpStatus.BAD_REQUEST);
    }
  }

  public ResponseEntity<String> updateQuestion(Question question) {
    try {
      questionDao.save(question);
      return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error updating question", HttpStatus.BAD_REQUEST);
    }
  }
}
