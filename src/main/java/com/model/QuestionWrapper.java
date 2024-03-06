package com.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class QuestionWrapper {

  private Integer id;
  private String questiontitle;
  private String category;
  private String option1;
  private String option2;
  private String option3;

  public QuestionWrapper(Integer id, String questiontitle, String category, String option1, String option2,
      String option3) {
    this.id = id;
    this.questiontitle = questiontitle;
    this.category = category;
    this.option1 = option1;
    this.option2 = option2;
    this.option3 = option3;
  }
}
