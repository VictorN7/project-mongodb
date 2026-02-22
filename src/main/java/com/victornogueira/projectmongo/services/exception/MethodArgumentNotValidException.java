package com.victornogueira.projectmongo.services.exception;

public class MethodArgumentNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MethodArgumentNotValidException(String id) {
		super(id);
	}
}
