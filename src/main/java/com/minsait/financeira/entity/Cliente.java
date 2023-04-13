package com.minsait.financeira.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id
	private String CPF;

	private String nome;

	private String telefone;

	private BigDecimal rendimentoMensal;

	@Embedded
	private Endereco endereco;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Emprestimo> emprestimos = new ArrayList<>();

	public boolean podeReceberEmprestimo(BigDecimal valorInicialNovoEmprestimo) {
		BigDecimal valorTotalEmprestimos = BigDecimal.ZERO;
		for (Emprestimo emprestimoExistente : this.getEmprestimos()) {
			valorTotalEmprestimos = valorTotalEmprestimos.add(emprestimoExistente.getValorInicial());
		}
		valorTotalEmprestimos = valorTotalEmprestimos.add(valorInicialNovoEmprestimo);
		BigDecimal limiteEmprestimo = this.getRendimentoMensal() != null
				? this.getRendimentoMensal().multiply(BigDecimal.TEN)
				: BigDecimal.ZERO;
		return valorTotalEmprestimos.compareTo(limiteEmprestimo) <= 0;
	}

	public BigDecimal calculaTotalEmprestimo(String cpf) {
		BigDecimal valorTotalEmprestimos = BigDecimal.ZERO;
		for (Emprestimo emprestimoExistente : this.getEmprestimos()) {
			valorTotalEmprestimos = valorTotalEmprestimos.add(emprestimoExistente.getValorInicial());
		}

		return valorTotalEmprestimos;
	}

	public void atualizarInformaçoes(@Valid Cliente clienteAtual) {
		if(clienteAtual.getNome() == null) {
		this.nome = clienteAtual.getNome();
		}
		if(clienteAtual.getTelefone() != null) {
			this.telefone = clienteAtual.getTelefone();
		}
		if(clienteAtual.getEndereco() != null) {
			this.endereco = clienteAtual.getEndereco();
		}
		if(clienteAtual.getRendimentoMensal() != null) {
			this.rendimentoMensal = clienteAtual.getRendimentoMensal();
		}
	}
	

}
