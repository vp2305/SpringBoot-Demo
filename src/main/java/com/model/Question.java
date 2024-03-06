package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// ORM: Object Relational Mapping -> Using JPA
// - It is a programming technique for converting data between incompatible type
@Data // Lombok annotation to create all the getters, setters, equals, hash, and
      // toString methods
@Entity // Since the table name is same as the class name, no need to mention the table
        // name
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String questiontitle;
  private String category;
  private String difficultylevel;
  private String option1;
  private String option2;
  private String option3;
  private String rightanswer;

}
