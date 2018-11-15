package org.jxnu.controller.Exception;

@SuppressWarnings("all")
public class CustomException extends Exception{

	private String message;
	
	public CustomException() {
		super();
	}

	public CustomException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
