package com.sk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.model.QuestionOption;

@Repository
public interface OptionRepository extends JpaRepository<QuestionOption, Long> {

}
