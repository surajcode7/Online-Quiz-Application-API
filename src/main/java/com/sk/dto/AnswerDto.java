package com.sk.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerDto {

	@NotNull(message = "Question id is required")
	private Long questionId;
	
	@NotNull(message = "Option id is required")
	private Long optionId;
}
