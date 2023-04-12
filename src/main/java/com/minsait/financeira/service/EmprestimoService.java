package com.minsait.financeira.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.minsait.financeira.entity.Cliente;
import com.minsait.financeira.entity.Emprestimo;
import com.minsait.financeira.exception.EmprestimoNotFoundException;
import com.minsait.financeira.exception.EntidadeEmUsoException;
import com.minsait.financeira.repository.EmprestimoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

	private static final String EMPRESTIMO_EM_USO = "Emprestimo de código %d não pode ser removido, pois está em uso";

	private final EmprestimoRepository emprestimoRepository;

	@Transactional
	public Emprestimo cadastrarEmprestimo(Emprestimo emprestimo) {

		Cliente cliente = emprestimo.getCliente();
		if (!cliente.podeReceberEmprestimo(emprestimo.getValorInicial())) {
			throw new IllegalArgumentException("O cliente já atingiu o limite de empréstimos permitido");
		}

		emprestimo.calcularValorFinal();
		emprestimo.getNumeroEmprestimos();

		return emprestimoRepository.save(emprestimo);
	}

	public Emprestimo buscarOuFalhar(Long emprestimoId) throws EmprestimoNotFoundException {
		return emprestimoRepository.findById(emprestimoId)
				.orElseThrow(() -> new EmprestimoNotFoundException(emprestimoId));
	}

	public void validarEmprestimo(Emprestimo emprestimo) {
		Long id = emprestimo.getId();
		if (id != null && emprestimoRepository.existsById(id)) {
			throw new IllegalArgumentException("Emprestimo já cadastrado");
		}
	}

	@Transactional
	public void deletarEmprestimo(Long emprestimoId) throws EmprestimoNotFoundException, EntidadeEmUsoException {
		try {
			emprestimoRepository.deleteById(emprestimoId);
			emprestimoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new EmprestimoNotFoundException(emprestimoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(EMPRESTIMO_EM_USO, emprestimoId));
		}
	}

}
