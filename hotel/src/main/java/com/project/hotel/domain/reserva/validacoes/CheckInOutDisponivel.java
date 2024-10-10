package com.project.hotel.domain.reserva.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.hotel.domain.ValidacaoException;
import com.project.hotel.domain.reserva.DadosCriarReserva;
import com.project.hotel.repository.ReservaRepository;

@Component
public class CheckInOutDisponivel implements ValidadorReservaDeQuarto {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public void validar(DadosCriarReserva dados) {

        boolean reservaConflitante = reservaRepository.existsByQuartoIdAndDataCheckInBeforeAndDataCheckOutAfter(
                dados.quartoId(), dados.dataCheckOut(), dados.dataCheckIn()
        );

        if (reservaConflitante) {
            throw new ValidacaoException("Reserva não pode ser agendada, há conflito de horários.");
        }
    }
}
