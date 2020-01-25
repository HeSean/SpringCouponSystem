package com.seanhed.exception;

public class IncorrectCredentialsException extends Exception {
	private static final long serialVersionUID = 1L;
	String msg;

	public IncorrectCredentialsException() {
	}

	public IncorrectCredentialsException(String msg) {
		super(msg);
	}

}
