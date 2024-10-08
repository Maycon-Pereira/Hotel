package com.project.hotel.domain.user;

import java.time.LocalDateTime;

public record DadosAtualizacaoUser(

		String id, 
		String nomeCompleto, 
		String email, 
		String senha, 
		String telefone, 
		String documentoIdentidade,
		LocalDateTime dataNascimento, 
		String endereco,
		boolean ativo
		) {

}
