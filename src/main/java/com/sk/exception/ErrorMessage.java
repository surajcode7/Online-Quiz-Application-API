package com.sk.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

	private Integer statusCode;
	private String  status;
	private String message;
	private LocalDateTime timeStamp;
	private Map<String,String> errors;// field levels errors
	
	
	public ErrorMessage(Integer statusCode, String status, String message, LocalDateTime timeStamp) {
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	
	
	
	public ErrorMessage(Integer statusCode, String message, LocalDateTime timeStamp) {
		this.statusCode = statusCode;
		this.message = message;
		this.timeStamp = timeStamp;
	}



	public ErrorMessage(Integer statusCode, String status,Map<String, String> errors,LocalDateTime timeStamp) {
		this.statusCode = statusCode;
		this.status = status;
		this.errors = errors;
		this.timeStamp = timeStamp;
	}
}
