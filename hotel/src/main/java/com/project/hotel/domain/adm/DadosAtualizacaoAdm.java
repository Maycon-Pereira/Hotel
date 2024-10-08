package com.project.hotel.domain.adm;

import java.time.LocalDateTime;

public record DadosAtualizacaoAdm(
		
		String nomeCompleto,
		String email,
		String senha,
		String telefone,
		LocalDateTime dataCadastro,
		String permissao,
		boolean ativo
		) {

	
	
}
