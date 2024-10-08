package com.project.hotel.domain.adm;

import java.time.LocalDateTime;

import com.project.hotel.entity.Adm;

public record DadosDetalhamentoAdm(String id, String nomeCompleto, String email, String senha, String telefone, LocalDateTime dataCadastro, String permissao) {

	public DadosDetalhamentoAdm(Adm admin) {
		this(admin.getId(), admin.getNomeCompleto(), admin.getEmail(), admin.getSenha(), admin.getTelefone(), admin.getDataCadastro(), admin.getPermissao());
	}
	
}
