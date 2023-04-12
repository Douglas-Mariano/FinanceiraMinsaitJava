package com.minsait.financeira.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minsait.financeira.enuns.StatusRelacinamento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import lombok.Data;

@Entity
@Data
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal valorInicial;

	private BigDecimal valorFinal;

	private LocalDate dataInicial;

	@Future
	private LocalDate dataFinal;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@JsonIgnore
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	private StatusRelacinamento relacionamento;

	public void calcularValorFinal() {
		this.valorFinal = this.relacionamento.calcularValorFinal(this);
	}

	public int getNumeroEmprestimos() {
		Cliente cliente = this.cliente;
		List<Emprestimo> emprestimos = cliente.getEmprestimos();
		return emprestimos.size();
	}

}
