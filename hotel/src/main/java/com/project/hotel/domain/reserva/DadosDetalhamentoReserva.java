package com.project.hotel.domain.reserva;

import java.time.LocalDateTime;

import com.project.hotel.entity.Reserva;

public record DadosDetalhamentoReserva(
		String id, String usuarioId, String quartoId, LocalDateTime dataCheckIn, LocalDateTime dataCheckOut, 
		LocalDateTime dataReserva, String metodoPagamento, double valorReserva, boolean disponivel, int capacidade) {

    public DadosDetalhamentoReserva(Reserva reserva) {
        this(reserva.getId(), reserva.getUsuarioId(), reserva.getQuartoId(), reserva.getDataCheckIn(), 
             reserva.getDataCheckOut(), reserva.getDataReserva(), 
             reserva.getMetodoPagamento(), reserva.getValorReserva(), 
             reserva.isDisponivel(), reserva.getCapacidade());
    }
}
