package com.sk.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.dto.OptionDto;
import com.sk.dto.QuestionRequest;
import com.sk.dto.QuestionResponse;
import com.sk.exception.ResourceNotFoundException;
import com.sk.model.Question;
import com.sk.model.QuestionOption;
import com.sk.model.Quiz;
import com.sk.repository.QuestionRepository;
import com.sk.repository.QuizRepository;
import com.sk.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepo;

	@Autowired
	private QuizRepository quizRepo;

	@Override
	public QuestionResponse addQuestion(Long quiz_id, QuestionRequest questionRequest) {

		Quiz quiz = quizRepo.findById(quiz_id)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id" + quiz_id));

		Question question = new Question();
		question.setQuiz(quiz);
		question.setType(questionRequest.getType());
		question.setText(questionRequest.getText());

		List<QuestionOption> options = questionRequest.getOptions().stream().map(optionDto -> {
			QuestionOption option = new QuestionOption();
			option.setText(optionDto.getText());
			option.setCorrect(optionDto.isCorrect());
			option.setQuestion(question);
			return option;
		}).collect(Collectors.toList());

		question.setOptions(options);

		Question saveQuestion = questionRepo.save(question);

		QuestionResponse questionResponse = new QuestionResponse();

		questionResponse.setId(saveQuestion.getId());
		questionResponse.setType(saveQuestion.getType());
		questionResponse.setText(saveQuestion.getText());

		List<OptionDto> optionResponse = saveQuestion.getOptions().stream().map(questionOpt -> {
			OptionDto opt = new OptionDto();
			opt.setId(questionOpt.getId());
			opt.setText(questionOpt.getText());
			opt.setCorrect(questionOpt.isCorrect());
			return opt;
		}).collect(Collectors.toList());

		questionResponse.setOptions(optionResponse);
		return questionResponse;
	}

	@Override
	public String deleteQuestion(Long quizId, Long question_id) {
		Quiz quiz = quizRepo.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("quiz not foundd with id:" + quizId));
		Question question = questionRepo.findById(question_id)
				.orElseThrow(() -> new ResourceNotFoundException("question is not found with id:" + question_id));

		if (!question.getQuiz().getId().equals(quiz.getId())) {
			throw new ResourceNotFoundException("Question does not belong to the given quiz");
		}
		questionRepo.deleteById(question_id);
		return "Question with id " + question_id + " deleted successfully from quiz id: " + quizId;
	}

	@Override
	public String deleteAllQuestions(Long quiz_id) {

		Quiz quiz = quizRepo.findById(quiz_id)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quiz_id));
		List<Question> questionList = questionRepo.findByQuizId(quiz.getId());

		if (!questionList.isEmpty()) {
			questionRepo.deleteByQuizId(quiz_id);
			return "All questions deleted successfully for quiz id:" + quiz_id;
		}

		return "questions not available to delete";
	}
}
