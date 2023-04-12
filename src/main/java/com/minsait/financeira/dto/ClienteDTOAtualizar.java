package com.minsait.financeira.dto;

import java.math.BigDecimal;

import com.minsait.financeira.entity.Endereco;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteDTOAtualizar {
	
	@NotNull
	@org.hibernate.validator.constraints.br.CPF
	private String CPF;

	private String nome;

	private String telefone;

	private BigDecimal rendimentoMensal;

	private Endereco endereco;

}
