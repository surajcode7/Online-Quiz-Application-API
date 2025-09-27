package com.sk.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuizResponse {

	private Long id;
	private String title;
	private int questionCount;
}
