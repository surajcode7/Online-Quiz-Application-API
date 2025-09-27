package com.sk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuizRequest {

	@NotBlank(message = "Quiz title is requried")
	private String title;
}
