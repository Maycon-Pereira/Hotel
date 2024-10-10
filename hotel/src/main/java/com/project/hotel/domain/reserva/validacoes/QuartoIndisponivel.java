package com.project.hotel.domain.reserva.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.hotel.domain.ValidacaoException;
import com.project.hotel.domain.reserva.DadosCriarReserva;
import com.project.hotel.entity.Quarto;
import com.project.hotel.repository.QuartoRepository;

@Component
public class QuartoIndisponivel  implements ValidadorReservaDeQuarto {

    @Autowired
    private QuartoRepository quartoRepository;

    @Override
    public void validar(DadosCriarReserva dados) {
    	
        Quarto quarto = quartoRepository.findById(dados.quartoId())
                .orElseThrow(() -> new ValidacaoException("Quarto não encontrado com o ID: " + dados.quartoId()));

        if (!quarto.isDisponivel()) {
            throw new ValidacaoException("A reserva não pode ser agendada, pois o quarto está indisponível!");
        }
    }
}
