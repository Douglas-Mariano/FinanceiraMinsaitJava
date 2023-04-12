package com.minsait.financeira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmprestimoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmprestimoNotFoundException(Long id) {
		super(String.format("Emprestimo com Codigo %s nao encontrado", id));
	}

}