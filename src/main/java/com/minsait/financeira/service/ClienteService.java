package com.minsait.financeira.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.minsait.financeira.entity.Cliente;
import com.minsait.financeira.exception.ClienteNotFoundException;
import com.minsait.financeira.exception.EntidadeEmUsoException;
import com.minsait.financeira.repository.ClienteRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private static final String CLIENTE_EM_USO = "Cliente de CPF %d não pode ser removido, pois está em uso";

	private final ClienteRepository clienteRepository;

	@Transactional
	public Cliente cadastrarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente buscarOuFalhar(String cpf) {
		return clienteRepository.findById(cpf).orElseThrow(() -> new ClienteNotFoundException(cpf));

	}

	@Transactional
	public Cliente alterarCliente(@Valid Cliente clienteAtual, String cpf) throws ClienteNotFoundException {

		var clienteParaAtualizar = clienteRepository.getReferenceById(cpf);
		clienteParaAtualizar.atualizarInformaçoes(clienteAtual);

		return clienteRepository.save(clienteAtual);
	}

	@Transactional
	public void deletarCliente(String cpf) {
		try {
			clienteRepository.deleteById(cpf);
			clienteRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new ClienteNotFoundException(cpf);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(CLIENTE_EM_USO, cpf));
		}
	}

}
