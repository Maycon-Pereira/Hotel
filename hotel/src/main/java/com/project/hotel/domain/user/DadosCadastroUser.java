package com.project.hotel.domain.user;

import java.time.LocalDateTime;

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
	    LocalDateTime dataNascimento,
	    @NotBlank
	    String endereco
	    
		) {

}
