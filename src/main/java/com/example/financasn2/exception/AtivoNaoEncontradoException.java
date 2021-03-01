package com.example.financasn2.exception;

public class AtivoNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = -3453766353790933374L;

	private final Long id;
	
	public AtivoNaoEncontradoException(final Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
 
}
