package com.project.hotel.domain.user;

import java.time.LocalDateTime;

public record DadosAtualizacaoUser(

		String id, 
		String nomeCompleto, 
		String email, 
		String senha, 
		String telefone, 
		String documentoIdentidade,
		String dataNascimento, 
		LocalDateTime dataCadastro,
		String endereco,
		boolean ativo
		) {

}
