package com.project.hotel.domain.user;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUser(
		
		@NotBlank
	    String nomeCompleto,
	    @NotBlank
	    String email,
	    @NotBlank
	    String senha,
	    @NotBlank
	    String telefone,
	    @NotBlank
	    String documentoIdentidade,
	    @NotBlank
	    String dataNascimento,
	    @Future
	    LocalDateTime dataCadastro,
	    @NotBlank
	    String endereco,
	    boolean ativo
	    
		) {

}
