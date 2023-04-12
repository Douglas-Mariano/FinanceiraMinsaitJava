package com.minsait.financeira.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.minsait.financeira.enuns.StatusRelacinamento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class EmprestimoDTOInput {

	private Long id;

	@NotNull(message = "O Valor é obrigatório")
	@PositiveOrZero
	private BigDecimal valorInicial;

	private LocalDate dataInicial;

	private LocalDate dataFinal;

	private StatusRelacinamento relacionamento;

}
