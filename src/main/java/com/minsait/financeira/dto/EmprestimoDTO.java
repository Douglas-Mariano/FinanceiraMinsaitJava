package com.minsait.financeira.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.minsait.financeira.entity.Cliente;
import com.minsait.financeira.enuns.StatusRelacinamento;

import lombok.Data;

@Data
public class EmprestimoDTO {

	private Long id;

	private BigDecimal valorInicial;

	private BigDecimal valorFinal;

	private LocalDate dataInicial;

	private LocalDate dataFinal;

	private StatusRelacinamento relacionamento;

	private Cliente cliente;

}
