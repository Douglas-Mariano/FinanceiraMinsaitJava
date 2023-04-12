package com.minsait.financeira.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Endereco {

	private String rua;

	private String numero;

	private String CEP;

}
