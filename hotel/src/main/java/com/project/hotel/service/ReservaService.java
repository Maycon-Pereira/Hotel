package com.project.hotel.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.domain.reserva.DadosAtualizacaoReserva;
import com.project.hotel.domain.reserva.DadosCriarReserva;
import com.project.hotel.domain.reserva.DadosDetalhamentoReserva;
import com.project.hotel.domain.reserva.validacoes.ValidadorReservaDeQuarto;
import com.project.hotel.entity.Quarto;
import com.project.hotel.entity.Reserva;
import com.project.hotel.entity.User;
import com.project.hotel.repository.QuartoRepository;
import com.project.hotel.repository.ReservaRepository;
import com.project.hotel.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuartoRepository quartoRepository;
	
	@Autowired
    private List<ValidadorReservaDeQuarto> validadores;

	public DadosDetalhamentoReserva criarReserva(@Valid DadosCriarReserva dados) throws Exception {
		Optional<User> procurado = userRepository.findById(dados.usuarioId());
		Optional<Quarto> procurado2 = quartoRepository.findById(dados.quartoId());
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do Usuario não encontrado na base");
		} else if(!procurado2.isPresent()) {
			throw new AccountNotFoundException("Id do Quarto não encontrado na base");
		}
		
		validadores.forEach(v -> v.validar(dados));
		
		Reserva reserva = new Reserva();

		reserva.setId(UUID.randomUUID().toString());
		reserva.setUsuarioId(dados.usuarioId());
		reserva.setQuartoId(dados.quartoId());
		reserva.setDataCheckIn(dados.dataCheckIn());
		reserva.setDataCheckOut(dados.dataCheckOut());
		reserva.setDataReserva(LocalDateTime.now());
		reserva.setMetodoPagamento(dados.metodoPagamento());
		reserva.setValorReserva(dados.valorReserva());
		reserva.setDisponivel(true);
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
		Optional<Reserva> procurado3 = reservaRepository.findById(id);
		if (!procurado3.isPresent()) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		Optional<User> procurado = userRepository.findById(dados.usuarioId());
		Optional<Quarto> procurado2 = quartoRepository.findById(dados.quartoId());
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do Usuario não encontrado na base");
		} else if(!procurado2.isPresent()) {
			throw new AccountNotFoundException("Id do Quarto não encontrado na base");
		}

		Reserva reserva = procurado3.get();

		reserva.setUsuarioId(dados.usuarioId());
		reserva.setQuartoId(dados.quartoId());
		reserva.setDataCheckIn(dados.dataCheckIn());
		reserva.setDataCheckOut(dados.dataCheckOut());
		reserva.setMetodoPagamento(dados.metodoPagamento());
		reserva.setValorReserva(dados.valorReserva());
		reserva.setCapacidade(dados.capacidade());

		Reserva saved = reservaRepository.save(reserva);

		return new DadosDetalhamentoReserva(saved);
	}

	public DadosDetalhamentoReserva excluirReserva(String id) throws Exception {
		Optional<Reserva> procurado = reservaRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		
		LocalDateTime agora = LocalDateTime.now();
	    Reserva reserva = procurado.get();

	    if (reserva.getDataCheckIn().isBefore(agora.plusDays(7))) {
	        throw new AccountNotFoundException("Não é possível cancelar a reserva, pois está muito em cima da hora!");
	    }
	    
	    reserva.setDisponivel(false);  
	    Reserva saved = reservaRepository.save(reserva);
		
        return new DadosDetalhamentoReserva(saved);
	}
}
