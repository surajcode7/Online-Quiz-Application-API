package com.sk.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {

	private Integer statusCode;
	private String  status;
	private String message;
	private Date timeStamp;
}
