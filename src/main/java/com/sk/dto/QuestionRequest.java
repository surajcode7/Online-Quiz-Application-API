package com.sk.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionRequest {

	@NotBlank(message = "question text is required")
	private String text;
	
	@NotBlank(message = "question type is required")
	private String type;// single,multiple,text
	
	@Size(min = 2, message = "At least 2 options required")
	private List<OptionDto> options;
}
