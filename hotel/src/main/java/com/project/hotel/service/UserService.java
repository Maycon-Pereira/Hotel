package com.project.hotel.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.domain.reserva.DadosDetalhamentoReserva;
import com.project.hotel.domain.reserva.DadosQuartoReservado;
import com.project.hotel.domain.user.DadosAtualizacaoUser;
import com.project.hotel.domain.user.DadosCadastroUser;
import com.project.hotel.domain.user.DadosDetalhamentoUser;
import com.project.hotel.entity.Reserva;
import com.project.hotel.entity.User;
import com.project.hotel.repository.ReservaRepository;
import com.project.hotel.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ReservaRepository reservaRepository;

    public DadosDetalhamentoUser criarUser(@Valid DadosCadastroUser dados) throws Exception {

    	var dataCadastro = LocalDateTime.now();
    	
        User user = new User();
        
        user.setId(UUID.randomUUID().toString());

        user.setNomeCompleto(dados.nomeCompleto());
        user.setEmail(dados.email());
        user.setSenha(dados.senha());
        user.setTelefone(dados.telefone());
        user.setDocumentoIdentidade(dados.documentoIdentidade());
        user.setDataNascimento(dados.dataNascimento());
        user.setDataCadastro(dataCadastro);
        user.setEndereco(dados.endereco());
        user.setAtivo(true);

        User saved = userRepository.save(user);

        return new DadosDetalhamentoUser(saved);
    }

    public DadosDetalhamentoUser detalharUser(String id) throws Exception {

        Optional<User> procurado = userRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }

        User user = procurado.get();
        return new DadosDetalhamentoUser(user);
    }

    public DadosDetalhamentoUser atualizarUser(String id, @Valid DadosAtualizacaoUser dados) throws Exception {

        Optional<User> procurado = userRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }

        User user = procurado.get();

        user.setNomeCompleto(dados.nomeCompleto());
        user.setEmail(dados.email());
        user.setSenha(dados.senha());
        user.setTelefone(dados.telefone());
        user.setDocumentoIdentidade(dados.documentoIdentidade());
        user.setDataNascimento(dados.dataNascimento());
        user.setEndereco(dados.endereco());

        User saved = userRepository.save(user);

        return new DadosDetalhamentoUser(saved);
    }

    public DadosDetalhamentoUser excluirUser(String id) throws Exception {

        Optional<User> procurado = userRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }

        User user = procurado.get();
        user.setAtivo(false);
        User saved = userRepository.save(user);

        return new DadosDetalhamentoUser(saved);
    }

	public DadosDetalhamentoReserva reservarQuarto(@Valid DadosQuartoReservado dados) throws Exception {
		
		 Optional<Reserva> procurado = reservaRepository.findById(dados.usuarioId());
		 Optional<Reserva> procurado2 = reservaRepository.findById(dados.quartoId());
	        if (!procurado.isPresent() || !procurado2.isPresent()) {
	            throw new AccountNotFoundException("Id não encontrado na base");
	        }

	        var reservaData = LocalDateTime.now();
	        
	        Reserva reserva = procurado.get();

	        reserva.setDataCheckIn(dados.dataCheckIn());
	        reserva.setDataCheckOut(dados.dataCheckOut());
	        reserva.setDataReserva(reservaData);
	        reserva.setMetodoPagamento(dados.metodoPagamento());
	        reserva.setValorReserva(dados.valorReserva());
	        reserva.setDisponivel(dados.disponivel());
	        reserva.setCapacidade(dados.capacidade());

	        Reserva saved = reservaRepository.save(reserva);

	        return new DadosDetalhamentoReserva(saved);
	}
}
