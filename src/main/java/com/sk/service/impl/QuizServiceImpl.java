package com.sk.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.dto.AnswerDto;
import com.sk.dto.OptionDto;
import com.sk.dto.OptionDtoNoAns;
import com.sk.dto.QuestionResponse;
import com.sk.dto.QuestionResponseNoAns;
import com.sk.dto.QuizRequest;
import com.sk.dto.QuizResponse;
import com.sk.dto.ScoreResponse;
import com.sk.dto.SubmitAnswerRequest;
import com.sk.exception.ResourceNotFoundException;
import com.sk.model.Question;
import com.sk.model.QuestionOption;
import com.sk.model.Quiz;
import com.sk.repository.QuestionRepository;
import com.sk.repository.QuizRepository;
import com.sk.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepo;

	@Autowired
	private QuestionRepository questionRepo;

	@Override
	public QuizResponse createQuiz(QuizRequest quizRequest) {

		Quiz quiz = new Quiz();

		quiz.setTitle(quizRequest.getTitle());

		if (quizRequest.getQuestions() != null && !quizRequest.getQuestions().isEmpty()) {

			List<Question> questionList = quizRequest.getQuestions().stream().map(qdto -> {
				Question question = new Question();
				question.setType(qdto.getType());
				question.setText(qdto.getText());
				question.setQuiz(quiz);

				List<QuestionOption> optionList = qdto.getOptions().stream().map(optDto -> {
					QuestionOption option = new QuestionOption();
					option.setText(optDto.getText());
					option.setCorrect(optDto.isCorrect());
					option.setQuestion(question);
					return option;
				}).collect(Collectors.toList());

				question.setOptions(optionList);

				return question;
			}).collect(Collectors.toList());

			quiz.setQuestions(questionList);
		}

		Quiz savedQuiz = quizRepo.save(quiz);

		QuizResponse response = new QuizResponse();

		response.setId(savedQuiz.getId());
		response.setTitle(savedQuiz.getTitle());

		// mapping Question to QuestionResponse
		List<QuestionResponse> questionResList = savedQuiz.getQuestions().stream().map(question -> {
			QuestionResponse qr = new QuestionResponse();

			qr.setId(question.getId());
			qr.setText(question.getText());
			qr.setType(question.getType());

			// mapping QuestionOption to OptionDto
			List<OptionDto> optDtoList = question.getOptions().stream().map(option -> {
				OptionDto optDto = new OptionDto();
				optDto.setId(option.getId());
				optDto.setText(option.getText());
				optDto.setCorrect(option.isCorrect());
				return optDto;
			}).collect(Collectors.toList());

			qr.setOptions(optDtoList);
			return qr;
		}).collect(Collectors.toList());

		response.setQuestions(questionResList);

		return response;
	}

	@Override
	public List<QuizResponse> getAllQuizzes() {

		List<Quiz> listQuizzes = quizRepo.findAll();

		List<QuizResponse> listQuizzesRes = listQuizzes.stream().map(quiz -> {
			QuizResponse quizRes = new QuizResponse();

			quizRes.setId(quiz.getId());
			quizRes.setTitle(quiz.getTitle());

			// mapping Question -> QuestionResponse
			List<QuestionResponse> questionList = quiz.getQuestions().stream().map(question -> {
				QuestionResponse qr = new QuestionResponse();

				qr.setId(question.getId());
				qr.setText(question.getText());
				qr.setType(question.getType());

				// mapping option->optionDto
				List<OptionDto> optList = question.getOptions().stream().map(option -> {

					OptionDto optDto = new OptionDto();
					optDto.setId(option.getId());
					optDto.setText(option.getText());
					optDto.setCorrect(option.isCorrect());
					return optDto;
				}).collect(Collectors.toList());

				qr.setOptions(optList);
				return qr;
			}).collect(Collectors.toList());
			// quizRes.setQuestionCount(quiz.getQuestions().size());

			quizRes.setQuestions(questionList);

			return quizRes;
		}).collect(Collectors.toList());

		return listQuizzesRes;
	}

	@Override
	public String deleteQuiz(Long id) {

		Quiz quiz = quizRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id:" + id));
		quizRepo.deleteById(quiz.getId());
		return "Quiz Deleted..!";
	}

	@Override
	public String deleteAllQuiz() {

		quizRepo.deleteAll();
		return "all Quizzes deleted";
	}

	// getting questions for quiz without answers
	@Override
	public List<QuestionResponseNoAns> getQuestionsForQuiz(Long quizId) {

		// quiz
		Quiz quiz = quizRepo.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id:" + quizId));

		// question list
		List<Question> questions = quiz.getQuestions();

		// converting questions to questions without answer
		List<QuestionResponseNoAns> resQuestionList = questions.stream().map(question -> {
			QuestionResponseNoAns qr = new QuestionResponseNoAns();
			qr.setId(question.getId());
			qr.setType(question.getType());
			qr.setText(question.getText());

			List<QuestionOption> options = question.getOptions();

			// converting options to options with no answer
			List<OptionDtoNoAns> resOptList = options.stream().map(opt -> {
				OptionDtoNoAns optNoAns = new OptionDtoNoAns();
				optNoAns.setId(opt.getId());
				optNoAns.setText(opt.getText());
				return optNoAns;
			}).collect(Collectors.toList());

			qr.setOptions(resOptList);
			return qr;
		}).collect(Collectors.toList());

		return resQuestionList;
	}

	// submit answers
	@Override
	public ScoreResponse submitAnswers(SubmitAnswerRequest answerRequest) {

		Quiz quiz = quizRepo.findById(answerRequest.getQuizId()).orElseThrow(
				() -> new ResourceNotFoundException("quiz not found with quiz-id: " + answerRequest.getQuizId()));

		int totalQuestiions = quiz.getQuestions().size();
		int score = 0;

		for (AnswerDto answer : answerRequest.getAnswers()) {

			Question question = questionRepo.findById(answer.getQuestionId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"question not found with question-id: " + answer.getQuestionId()));

			if (!question.getQuiz().getId().equals(quiz.getId())) {
				throw new ResourceNotFoundException(
						"question with id: " + question.getId() + " does not belongs to quiz id: " + quiz.getId());
			}

			boolean isCorrect = question.getOptions().stream()
					.anyMatch(option -> option.getId().equals(answer.getOptionId()) && option.isCorrect());

			if (isCorrect)
				score++;
		}
		return new ScoreResponse(score, totalQuestiions);
	}
}
