package com.project.hotel.domain.user;

import java.time.LocalDateTime;

import com.project.hotel.entity.User;

public record DadosDetalhamentoUser(String id, String nomeCompleto, String email, String senha, String telefone, String documentoIdentidade, LocalDateTime dataNascimento, String endereco, boolean ativo) {

	public DadosDetalhamentoUser(User user) {
		this(user.getId(), user.getNomeCompleto(), user.getEmail(), user.getSenha(), user.getTelefone(), user.getDocumentoIdentidade(), user.getDataNascimento(), user.getEndereco(), user.isAtivo());
	}
	
}
