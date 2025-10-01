package com.sk.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScoreResponse {

	private int score;
	private int total;

	public ScoreResponse(int score, int total) {
		this.score = score;
		this.total = total;
	}

}
