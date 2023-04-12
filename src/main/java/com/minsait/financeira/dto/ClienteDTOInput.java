package com.minsait.financeira.dto;

import java.math.BigDecimal;
import java.util.List;

import com.minsait.financeira.entity.Emprestimo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ClienteDTOInput {

	@org.hibernate.validator.constraints.br.CPF
	private String CPF;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@NotBlank(message = "O telefone é obrigatório")
	private String telefone;

	@NotNull(message = "O Rendimento é obrigatório")
	@PositiveOrZero
	private BigDecimal rendimentoMensal;

	@Valid
	private EnderecoDTOInput endereco;

	@Valid
	private List<Emprestimo> emprestimos;

}
