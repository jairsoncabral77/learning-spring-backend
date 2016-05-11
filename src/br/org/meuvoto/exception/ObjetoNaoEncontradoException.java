package br.org.meuvoto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="O objeto com o Id especificado não foi encontrado")
public class ObjetoNaoEncontradoException extends RuntimeException {

	public ObjetoNaoEncontradoException() {
	}
	
	public ObjetoNaoEncontradoException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
