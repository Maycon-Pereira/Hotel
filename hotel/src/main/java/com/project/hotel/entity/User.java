package com.project.hotel.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@Setter
public class User {
	
	@Id
	private String id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String telefone;
    private String documentoIdentidade;
    private String dataNascimento;
    private LocalDateTime dataCadastro;
    private String endereco;
    private boolean ativo;
}
