package com.sk.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubmitAnswerRequest {

	@NotNull(message = "Quiz ID is required")
    private Long quizId;

    @Size(min = 1, message = "At least one answer must be provided")
    private List<AnswerDto> answers;
}
