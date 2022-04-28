package com.java.exception;

public class NoBooksException extends Exception{
	private String message;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoBooksException(String message){
		this.message = message;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
