package com.sk.dto;

import java.util.List;

public class ResponseMessage {

	private Integer statusCode;
	private String  status;
	private String message;
	private Object data;
	private List<?> list;
	
	public ResponseMessage(Integer statusCode, String status, String message, Object data, List<?> list) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.data = data;
		this.list = list;
	}

	public ResponseMessage(Integer statusCode, String status, String message, Object data) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public ResponseMessage(Integer statusCode, String status, String message, List<?> list) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.list = list;
	}

	public ResponseMessage(Integer statusCode, String status, String message) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
	
	
}
