package com.minsait.financeira.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minsait.financeira.dto.ClienteDTOAtualizar;
import com.minsait.financeira.dto.ClienteDTOInput;
import com.minsait.financeira.entity.Cliente;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClienteDTOInputDisassembler {

	private final ModelMapper modelMapper;

	public Cliente toModel(ClienteDTOInput clienteDTOInput) {
		return modelMapper.map(clienteDTOInput, Cliente.class);
	}

	public void copyToDomainObject(ClienteDTOInput clienteDTOInput, Cliente cliente) {
		modelMapper.map(clienteDTOInput, cliente);
	}

	public void copyToDomainObject(@Valid ClienteDTOAtualizar clienteInput, Cliente clienteAtual) {
		modelMapper.map(clienteInput, clienteAtual);
	}

}
