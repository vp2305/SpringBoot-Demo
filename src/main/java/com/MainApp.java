package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * QuestionController
 * - Controller: Accept the request from the user
 * - Service layer: Mention all the business logic and what to do with the
 * request
 * - DAO layer: Connect to the database and fetch the data
 */

@SpringBootApplication
public class MainApp {
  public static void main(String[] args) {
    SpringApplication.run(MainApp.class, args);
  }
}
