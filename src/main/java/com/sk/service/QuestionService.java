package com.sk.service;

import com.sk.dto.QuestionRequest;
import com.sk.dto.QuestionResponse;

public interface QuestionService {

	public QuestionResponse addQuestion(Long quiz_id, QuestionRequest questionRequest);
	public String deleteQuestion(Long quizId,Long question_id);
	public String deleteAllQuestions(Long quiz_id);
	
}
