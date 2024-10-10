package com.project.hotel.domain.reserva;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriarReserva(
		
		@NotBlank
		String usuarioId,
		@NotBlank
	    String quartoId,
	    LocalDateTime dataCheckIn,
	    LocalDateTime dataCheckOut,
	    LocalDateTime dataReserva,
	    @NotBlank
	    String metodoPagamento,
	    @NotNull
	    double valorReserva,
	    @NotNull
	    boolean disponivel,
	    @NotNull
	    int capacidade
	    ) {

}
