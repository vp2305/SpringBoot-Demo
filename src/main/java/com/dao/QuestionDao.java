package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Question;

// JpaRepository<Question, Integer> -> Question is the model class and Integer is the type of primary key
// Need to use HQL or JPQL
@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
  List<Question> findByCategory(String categoryName);

  @Query(value = "SELECT * FROM question q WHERE q.category=:categoryName ORDER BY RANDOM() LIMIT :numberOfQuestions", nativeQuery = true)
  List<Question> findRandomQuestionsByCategory(String categoryName, int numberOfQuestions);
}
