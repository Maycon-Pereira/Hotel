package com.project.hotel.domain.quarto;

public record DadosAtualizacaoQuarto(
		
	     String numero,
	     String tipo,
	     int capacidade,
	     double precoPorNoite,
	     boolean disponivel
	     ) {

}
