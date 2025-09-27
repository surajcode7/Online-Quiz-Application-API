package com.sk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OptionDto {

	private Long id;
	
	@NotBlank(message = "option text is required")
	private String text;
	
	private boolean correct;
}
