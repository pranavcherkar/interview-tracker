package com.lightyagami.interviewtracker.exception;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException{
	public CustomException(String message) {
        super(message);
    }
}
