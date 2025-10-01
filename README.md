# Online-Quiz-Application-API
A Spring Boot RESTful Quiz Application that allows users to create quizzes, add questions and options, take quizzes, submit answers, and get scores. Built with Spring Boot, JPA/Hibernate, and MySQL.

Features:

Create, update, and delete quizzes.

Add, view, and delete questions with multiple options.

Hide correct answers when displaying quizzes for taking.

Submit answers and calculate scores automatically.

Exception handling with proper validation messages.

Setup Instructions:

1:Clone the repository:

https://github.com/surajcode7/Online-Quiz-Application-API.git

2.setup database:

spring.datasource.url=jdbc:mysql://localhost:3306/QuizAppDB?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password

3:Run the application

mvn spring-boot:run
The application will run on http://localhost:8585

endpoints:

1: crating Quiz
endpoint:post request==>http://localhost:8585/sk/Quizs/add
input:{
  "title": "Spring Boot Quiz",
  "questions": [
    {
      "text": "Which annotation is used to create a REST controller in Spring Boot?",
      "type": "single",
      "options": [
        { "text": "@RestController", "correct": true },
        { "text": "@Controller", "correct": false },
        { "text": "@Service", "correct": false }
      ]
    },
    {
      "text": "Which are valid dependency injection types in Spring?",
      "type": "multiple",
      "options": [
        { "text": "Constructor Injection", "correct": true },
        { "text": "Setter Injection", "correct": true },
        { "text": "Interface Injection", "correct": false }
      ]
    }
  ]
}

2: getting question list of specific quiz without answers

endpoint:get request==>http://localhost:8585/sk/api/quiz/getQuestions/{quiz_id}

3: submitting test
endpoint:post request==>http://localhost:8585/sk/api/quiz/sumbit/answer

input:{
  "quizId": 1,
  "answers": [
    {
      "questionId": 1,
      "optionId": 1
    },
    {
      "questionId": 2,
      "optionId": 7
    }
  ]
}

4:adding question in quiz with quiz_id
endpoint:post request==>http://localhost:8585/sk/api/question/{quizId}/add
input:{
  "text": "What is the capital of India?",
  "type": "single",
  "options": [
    {
      "text": "Delhi",
      "correct": true
    },
    {
      "text": "Mumbai",
      "correct": false
    },
    {
      "text": "Kolkata",
      "correct": false
    }
  ]
}

5: get All Quizzes

endpoint:get request==>http://localhost:8585/sk/api/quiz/getAll

6:delete Quiz by id

endpoint:delete request==>http://localhost:8585/sk/Quizs/delete/{quiz_id}

7:delete All quizzes

endpoint:delete request==>http://localhost:8585/sk/Quizs/delete/All

8:delete question by id

endpoint:deleter request==>http://localhost:8585/sk/api/question/{quizId}/delete/{questionId}

9:delete all questions by quiz id

endpoint:delete request==>http://localhost:8585/sk/api/question/{quizId}/delete







