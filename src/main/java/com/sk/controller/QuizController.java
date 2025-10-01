package com.sk.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.dto.QuestionResponseNoAns;
import com.sk.dto.QuizRequest;
import com.sk.dto.QuizResponse;
import com.sk.dto.ResponseMessage;
import com.sk.dto.ScoreResponse;
import com.sk.dto.SubmitAnswerRequest;
import com.sk.service.QuizService;
import com.sk.utility.Constants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sk/api/quiz")
public class QuizController {

	@Autowired
	private QuizService service;

	// creating quiz
	@PostMapping("/add")
	public ResponseEntity<ResponseMessage> createQuiz(@Valid @RequestBody QuizRequest quizRequest) {

		QuizResponse addedQuiz = service.createQuiz(quizRequest);

		if (addedQuiz != null) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
					"Quiz added Successfully...!", addedQuiz));
		} else {
			return ResponseEntity
					.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "Quiz not added"));
		}
	}

	// get questions list without answers
	@GetMapping("/getQuestions/{quiz_id}")
	public ResponseEntity<ResponseMessage> getQuestionListNoAns(@PathVariable Long quiz_id) {

		List<QuestionResponseNoAns> questionsList = service.getQuestionsForQuiz(quiz_id);

		if (!questionsList.isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
					"featched all questions successfully", questionsList));
		} else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,
					"questions not found..!"));
		}
	}

	// submitting the answers and getting result
	@PostMapping("/sumbit/answer")
	public ResponseEntity<ResponseMessage> submitAnswers(@RequestBody SubmitAnswerRequest answerRequest) {

		ScoreResponse submitAnswers = service.submitAnswers(answerRequest);

		if (submitAnswers != null) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
					"test sumbmitted successfully..!", submitAnswers));
		} else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,
					"test not submitted..!"));
		}
	}

	// getting all quizzes
	@GetMapping("/getAll")
	public ResponseEntity<ResponseMessage> getAllQuizzes() {

		List<QuizResponse> allQuizzesRes = service.getAllQuizzes();

		if (!allQuizzesRes.isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
					"All Quizzes list feateched Successfully...!", allQuizzesRes));
		} else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,
					"Quizzes not available..!"));
		}
	}

	// delete quiz by id
	@DeleteMapping("/delete/{quiz_id}")
	public ResponseEntity<ResponseMessage> deleteQuiz(@PathVariable Long quiz_id) {

		String deleteQuizRes = service.deleteQuiz(quiz_id);

		if (deleteQuizRes != null) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, deleteQuizRes));
		} else {
			return ResponseEntity
					.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "Quiz not deleted"));
		}
	}

	// deleting all quizzes
	@DeleteMapping("/delete/All")
	public ResponseEntity<ResponseMessage> deleteAllQuizzes() {

		String deleteAllQuizRes = service.deleteAllQuiz();
		if (deleteAllQuizRes != null) {
			return ResponseEntity
					.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, deleteAllQuizRes));
		} else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,
					"quizzes not deleted..!"));
		}
	}
}
