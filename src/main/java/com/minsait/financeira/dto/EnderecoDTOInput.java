package com.minsait.financeira.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoDTOInput {

	@NotBlank(message = "A rua é obrigatório")
	private String rua;

	@NotBlank(message = "O nº é obrigatório")
	private String numero;

	@NotBlank(message = "O CEP é obrigatório")
	private String CEP;

}
