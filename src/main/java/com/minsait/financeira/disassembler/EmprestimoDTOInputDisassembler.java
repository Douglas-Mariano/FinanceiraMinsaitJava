package com.minsait.financeira.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minsait.financeira.dto.EmprestimoDTOInput;
import com.minsait.financeira.entity.Emprestimo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmprestimoDTOInputDisassembler {

	private final ModelMapper modelMapper;

	public Emprestimo toModel(EmprestimoDTOInput emprestimoDTOInput) {
		return modelMapper.map(emprestimoDTOInput, Emprestimo.class);
	}

	public void copyToDomainObject(EmprestimoDTOInput emprestimoDTOInput, Emprestimo emprestimo) {
		modelMapper.map(emprestimoDTOInput, emprestimo);
	}

}