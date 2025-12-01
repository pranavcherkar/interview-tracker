package com.lightyagami.interviewtracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lightyagami.interviewtracker.dto.ApiResponse;

public class GlobalExceptionHandler {
	 @ExceptionHandler(CustomException.class)
	    public ResponseEntity<ApiResponse> handleCustomException(CustomException ex) {
	        ApiResponse response = new ApiResponse(false, ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiResponse> handleGeneralException(Exception ex) {
	        ApiResponse response = new ApiResponse(false, "Unexpected error: " + ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
