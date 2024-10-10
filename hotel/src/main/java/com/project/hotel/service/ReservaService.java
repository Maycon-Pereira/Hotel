package com.project.hotel.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.domain.reserva.DadosAtualizacaoReserva;
import com.project.hotel.domain.reserva.DadosCriarReserva;
import com.project.hotel.domain.reserva.DadosDetalhamentoReserva;
import com.project.hotel.entity.Reserva;
import com.project.hotel.repository.ReservaRepository;

import jakarta.validation.Valid;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public DadosDetalhamentoReserva criarReserva(@Valid DadosCriarReserva dados) {
        Reserva reserva = new Reserva();
        
        reserva.setId(UUID.randomUUID().toString());
        reserva.setUsuarioId(dados.usuarioId());
        reserva.setQuartoId(dados.quartoId());
        reserva.setDataCheckIn(dados.dataCheckIn());
        reserva.setDataCheckOut(dados.dataCheckOut());
        reserva.setDataReserva(LocalDateTime.now());
        reserva.setMetodoPagamento(dados.metodoPagamento());
        reserva.setValorReserva(dados.valorReserva());
        reserva.setDisponivel(dados.disponivel());
        reserva.setCapacidade(dados.capacidade());
        
        Reserva saved = reservaRepository.save(reserva);
        
        return new DadosDetalhamentoReserva(saved);
    }

    public DadosDetalhamentoReserva detalharReserva(String id) throws Exception {
        Optional<Reserva> procurado = reservaRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }
        
        Reserva reserva = procurado.get();
        return new DadosDetalhamentoReserva(reserva);
    }

    public DadosDetalhamentoReserva atualizarReserva(String id, @Valid DadosAtualizacaoReserva dados) throws Exception {
        Optional<Reserva> procurado = reservaRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }
        
        Reserva reserva = procurado.get();
        
        reserva.setUsuarioId(dados.usuarioId());
        reserva.setQuartoId(dados.quartoId());
        reserva.setDataCheckIn(dados.dataCheckIn());
        reserva.setDataCheckOut(dados.dataCheckOut());
        reserva.setMetodoPagamento(dados.metodoPagamento());
        reserva.setValorReserva(dados.valorReserva());
        reserva.setDisponivel(dados.disponivel());
        reserva.setCapacidade(dados.capacidade());
        
        Reserva saved = reservaRepository.save(reserva);
        
        return new DadosDetalhamentoReserva(saved);
    }

    public void excluirReserva(String id) throws Exception {
        Optional<Reserva> procurado = reservaRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }

        reservaRepository.deleteById(id);
    }
}
