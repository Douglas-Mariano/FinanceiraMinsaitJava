package com.minsait.financeira.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minsait.financeira.dto.EmprestimoDTO;
import com.minsait.financeira.entity.Emprestimo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmprestimoDTOAssembler {

	private final ModelMapper modelMapper;

	public EmprestimoDTO toModel(Emprestimo emprestimo) {
		return modelMapper.map(emprestimo, EmprestimoDTO.class);
	}

	public List<EmprestimoDTO> toCollectionModel(List<Emprestimo> emprestimos) {
		return emprestimos.stream().map(emprestimo -> toModel(emprestimo)).collect(Collectors.toList());
	}

}
