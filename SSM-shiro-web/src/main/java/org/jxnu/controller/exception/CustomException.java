package org.jxnu.controller.exception;

/**
 * 自定义异常类
 * @author MR.S
 *
 */
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
