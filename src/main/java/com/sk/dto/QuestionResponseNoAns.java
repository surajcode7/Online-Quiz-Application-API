package com.sk.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionResponseNoAns {

	private Long id;
	private String text;
	private String type;
	private List<OptionDtoNoAns> options;
}
