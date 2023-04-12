package com.minsait.financeira.dto;

import java.math.BigDecimal;
import java.util.List;

import com.minsait.financeira.entity.Emprestimo;
import com.minsait.financeira.entity.Endereco;

import lombok.Data;

@Data
public class ClienteDTO {
	
	private String CPF;

	private String nome;

	private String telefone;

	private BigDecimal rendimentoMensal;

	private Endereco endereco;

	private List<Emprestimo> emprestimos;

}
