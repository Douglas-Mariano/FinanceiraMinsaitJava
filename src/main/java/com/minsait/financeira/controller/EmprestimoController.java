package com.minsait.financeira.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.financeira.assembler.EmprestimoDTOAssembler;
import com.minsait.financeira.disassembler.EmprestimoDTOInputDisassembler;
import com.minsait.financeira.dto.EmprestimoDTO;
import com.minsait.financeira.dto.EmprestimoDTOInput;
import com.minsait.financeira.entity.Cliente;
import com.minsait.financeira.entity.Emprestimo;
import com.minsait.financeira.exception.EmprestimoNotFoundException;
import com.minsait.financeira.exception.EntidadeEmUsoException;
import com.minsait.financeira.repository.EmprestimoRepository;
import com.minsait.financeira.service.ClienteService;
import com.minsait.financeira.service.EmprestimoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/financeira/clientes/{cpf}/emprestimos")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EmprestimoController {

	private final EmprestimoRepository emprestimoRepository;

	private final EmprestimoService emprestimoService;

	private final ClienteService clienteService;

	private final EmprestimoDTOAssembler emprestimoDTOAssembler;

	private final EmprestimoDTOInputDisassembler emprestimoDTOInputDisassembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmprestimoDTO cadastrarEmprestimo(@Valid @RequestBody EmprestimoDTOInput emprestimoInput,
			@PathVariable String cpf) {

		Cliente cliente = clienteService.buscarOuFalhar(cpf);

		Emprestimo emprestimo = emprestimoDTOInputDisassembler.toModel(emprestimoInput);

		emprestimo.setCliente(cliente);

		emprestimo = emprestimoService.cadastrarEmprestimo(emprestimo);

		return emprestimoDTOAssembler.toModel(emprestimo);
	}

	@GetMapping
	public List<EmprestimoDTO> recuperarTodosEmprestimos() {
		List<Emprestimo> todosEmprestimos = emprestimoRepository.findAll();

		return emprestimoDTOAssembler.toCollectionModel(todosEmprestimos);
	}

	@GetMapping("/{id}")
	public EmprestimoDTO recuperarCliente(@PathVariable Long id) throws EmprestimoNotFoundException {
		Emprestimo emprestimo = emprestimoService.buscarOuFalhar(id);

		return emprestimoDTOAssembler.toModel(emprestimo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Long id) throws EmprestimoNotFoundException, EntidadeEmUsoException {
		emprestimoService.deletarEmprestimo(id);
	}
}