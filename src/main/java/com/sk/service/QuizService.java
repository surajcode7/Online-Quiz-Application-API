package com.sk.service;

import java.util.List;

import com.sk.dto.QuestionResponseNoAns;
import com.sk.dto.QuizRequest;
import com.sk.dto.QuizResponse;
import com.sk.dto.ScoreResponse;
import com.sk.dto.SubmitAnswerRequest;

public interface QuizService {

	public QuizResponse createQuiz(QuizRequest quizRequest);
	public List<QuestionResponseNoAns> getQuestionsForQuiz(Long quizId);
	public ScoreResponse submitAnswers(SubmitAnswerRequest answerRequest);
	
	public List<QuizResponse> getAllQuizzes();
	public String deleteQuiz(Long id);
	public String deleteAllQuiz();
}
