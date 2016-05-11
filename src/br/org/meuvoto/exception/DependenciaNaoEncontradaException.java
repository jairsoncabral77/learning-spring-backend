package br.org.meuvoto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED)
public class DependenciaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DependenciaNaoEncontradaException() {
		super();
	}

	public DependenciaNaoEncontradaException(String message) {
		super(message);
	}	
	
}
