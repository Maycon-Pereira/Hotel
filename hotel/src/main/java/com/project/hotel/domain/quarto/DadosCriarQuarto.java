package com.project.hotel.domain.quarto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriarQuarto(
		
		 @NotBlank
	     String numero,
	     @NotBlank
	     String tipo,
	     @NotNull
	     int capacidade,
	     @NotNull
	     double precoPorNoite,
	     @NotNull
	     boolean disponivel
	     
	    ) {

}
