package com.project.hotel.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "adm")
@Entity(name = "Adm")
@Getter
@Setter
public class Adm {
	
	@Id
	private String id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String telefone;
    private LocalDateTime dataCadastro;
    private String permissao;
    
}
