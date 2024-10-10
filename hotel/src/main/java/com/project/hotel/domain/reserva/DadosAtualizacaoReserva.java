package com.project.hotel.domain.reserva;

import java.time.LocalDateTime;

public record DadosAtualizacaoReserva(
		
		String id, 
		String usuarioId, 
		String quartoId, 
		LocalDateTime dataCheckIn, 
		LocalDateTime dataCheckOut, 
		LocalDateTime dataReserva, 
		String metodoPagamento, 
		double valorReserva, 
		boolean disponivel, 
		int capacidade
		) {

}
