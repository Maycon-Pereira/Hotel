package com.project.hotel.domain.reserva;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record DadosCriarReserva(
		
		@NotBlank
		String id,
		@NotBlank
		String usuarioId,
		@NotBlank
	    String quartoId,
	    LocalDateTime dataCheckIn,
	    LocalDateTime dataCheckOut,
	    LocalDateTime dataReserva,
	    @NotBlank
	    String metodoPagamento,
	    @NotBlank
	    double valorReserva,
	    @NotBlank
	    boolean disponivel,
	    @NotBlank
	    int capacidade
	    ) {

}
