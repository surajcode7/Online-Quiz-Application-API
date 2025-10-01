package com.sk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	//deleting all question belonging to quiz id
	public void deleteByQuizId(Long quizId);
	
	//getting all question list by quiz id
	public List<Question> findByQuizId(Long quizId);
}
