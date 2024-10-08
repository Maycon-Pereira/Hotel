package com.project.hotel.domain.adm;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAdm(
		
		@NotBlank
		String nomeCompleto,
		@NotBlank
		String email,
		@NotBlank
		String senha,
		@NotBlank
		String telefone,
		@Future
		LocalDateTime dataCadastro,
		@NotBlank
		String permissao
		
		){

}
