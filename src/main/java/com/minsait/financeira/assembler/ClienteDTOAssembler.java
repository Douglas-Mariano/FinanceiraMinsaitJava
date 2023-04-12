package com.minsait.financeira.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.minsait.financeira.dto.ClienteDTO;
import com.minsait.financeira.entity.Cliente;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClienteDTOAssembler {

	private final ModelMapper modelMapper;

	public ClienteDTO toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDTO.class);
	}

	public List<ClienteDTO> toCollectionModel(List<Cliente> clientes) {
		return clientes.stream().map(cliente -> toModel(cliente)).collect(Collectors.toList());
	}
}
