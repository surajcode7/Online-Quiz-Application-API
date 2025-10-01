package com.sk.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.dto.QuestionRequest;
import com.sk.dto.QuestionResponse;
import com.sk.dto.ResponseMessage;
import com.sk.service.QuestionService;
import com.sk.utility.Constants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sk/api/question/{quizId}")
@Validated
public class QuestionController {

	@Autowired
	private QuestionService service;

	// adding question
	@PostMapping("/add")
	public ResponseEntity<ResponseMessage> addQuestion(@Valid @PathVariable("quizId") Long quizId,
			@RequestBody QuestionRequest questionRequest) {

		QuestionResponse questionResponse = service.addQuestion(quizId, questionRequest);

		if (questionResponse != null) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
					"Question added Successfully...!", questionResponse));
		} else {
			return ResponseEntity.ok(
					new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "Question not added"));
		}
	}

	// deleting question
	@DeleteMapping("/delete/{questionId}")
	public ResponseEntity<ResponseMessage> deleteQuestion(@PathVariable("quizId") Long quizId,
			@PathVariable("questionId") Long questionId) {

		String deleteQuestionRes = service.deleteQuestion(quizId, questionId);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, deleteQuestionRes));
	}

	// deleting all question in quiz
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseMessage> deleteAllQuestions(@PathVariable("quizId") Long quizId) {

		String deleteAllQuestionsRes = service.deleteAllQuestions(quizId);
		return ResponseEntity
				.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, deleteAllQuestionsRes));
	}

}
