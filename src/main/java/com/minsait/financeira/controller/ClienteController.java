package com.minsait.financeira.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.financeira.assembler.ClienteDTOAssembler;
import com.minsait.financeira.disassembler.ClienteDTOInputDisassembler;
import com.minsait.financeira.dto.ClienteDTO;
import com.minsait.financeira.dto.ClienteDTOAtualizar;
import com.minsait.financeira.dto.ClienteDTOInput;
import com.minsait.financeira.entity.Cliente;
import com.minsait.financeira.exception.ClienteNotFoundException;
import com.minsait.financeira.exception.EntidadeEmUsoException;
import com.minsait.financeira.repository.ClienteRepository;
import com.minsait.financeira.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/financeira/clientes")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteRepository clienteRepository;

	private final ClienteService clienteService;

	private final ClienteDTOAssembler clienteDTOAssembler;

	private final ClienteDTOInputDisassembler clienteDTOInputDisassembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO cadastrarCliente(@Valid @RequestBody ClienteDTOInput clienteInput) {
		Cliente cliente = clienteDTOInputDisassembler.toModel(clienteInput);

		cliente = clienteService.cadastrarCliente(cliente);

		return clienteDTOAssembler.toModel(cliente);
	}

	@GetMapping
	public List<ClienteDTO> recuperarTodosClientes() {
		List<Cliente> todosClientes = clienteRepository.findAll();

		return clienteDTOAssembler.toCollectionModel(todosClientes);
	}

	@GetMapping("/{cpf}")
	public ClienteDTO recuperarCliente(@PathVariable String cpf) {
		Cliente cliente = clienteService.buscarOuFalhar(cpf);

		return clienteDTOAssembler.toModel(cliente);
	}

	@PutMapping("/{cpf}")
	public ClienteDTO alterarCliente(@PathVariable String cpf, @Valid @RequestBody ClienteDTOAtualizar clienteInput)
			throws ClienteNotFoundException {
		Cliente clienteAtual = clienteService.buscarOuFalhar(cpf);

		clienteDTOInputDisassembler.copyToDomainObject(clienteInput, clienteAtual);

		clienteAtual = clienteService.alterarCliente(clienteAtual, cpf);

		return clienteDTOAssembler.toModel(clienteAtual);
	}

	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable String cpf) throws ClienteNotFoundException, EntidadeEmUsoException {
		clienteService.deletarCliente(cpf);
	}
}