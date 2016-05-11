package br.org.meuvoto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class ConflitoPeriodoMandatoException extends RuntimeException {

	public ConflitoPeriodoMandatoException() {
	}
	
	public ConflitoPeriodoMandatoException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
