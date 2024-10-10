package com.project.hotel.domain.reserva.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.hotel.domain.ValidacaoException;
import com.project.hotel.domain.reserva.DadosCriarReserva;
import com.project.hotel.entity.Quarto;
import com.project.hotel.repository.QuartoRepository;

@Component
public class CapacidadeTotal implements ValidadorReservaDeQuarto {

    @Autowired
    private QuartoRepository quartoRepository;

    @Override
    public void validar(DadosCriarReserva dados) {
        // Verifica se o quarto existe
        Quarto quarto = quartoRepository.findById(dados.quartoId())
                .orElseThrow(() -> new ValidacaoException("Quarto não encontrado com o ID: " + dados.quartoId()));

        if (dados.capacidade() > quarto.getCapacidade() || dados.capacidade() < 0 || dados.capacidade() == 0) {
            throw new ValidacaoException("A reserva não pode ser agendada, pois o quarto suporta no máximo "
                    + quarto.getCapacidade() + " pessoas, mas foi solicitado para " + dados.capacidade() + " pessoas.");
        }
    }
}
