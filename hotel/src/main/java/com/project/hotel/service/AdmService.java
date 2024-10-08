package com.project.hotel.service;

import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.domain.adm.DadosAtualizacaoAdm;
import com.project.hotel.domain.adm.DadosCadastroAdm;
import com.project.hotel.domain.adm.DadosDetalhamentoAdm;
import com.project.hotel.entity.Adm;
import com.project.hotel.repository.AdmRepository;

import jakarta.validation.Valid;

@Service
public class AdmService {
	
	@Autowired
	private AdmRepository admRepository;

	public DadosDetalhamentoAdm criarAdmin(@Valid DadosCadastroAdm dados) throws Exception {
		
		Adm admin = new Adm();
		
		admin.setNomeCompleto(dados.nomeCompleto());
		admin.setEmail(dados.email());
		admin.setSenha(dados.senha());
		admin.setTelefone(dados.telefone());
		admin.setDataCadastro(dados.dataCadastro());
		admin.setPermissao(dados.permissao());
		
		Adm saved = admRepository.save(admin);
		
		return new DadosDetalhamentoAdm(saved);
	}

	public DadosDetalhamentoAdm detalharAdm(String id) throws Exception {

		Optional<Adm> procurado = admRepository.findById(id);
		if (!procurado.isPresent()) {
        	throw new AccountNotFoundException("Id não encontrado na base");
        }
		
		Adm adm = procurado.get();
		return new DadosDetalhamentoAdm(adm);
	}
	
	public DadosDetalhamentoAdm atualizarAdmin(String id, @Valid DadosAtualizacaoAdm dados) throws Exception {
		
		Optional<Adm> procurado = admRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		
		Adm admin = procurado.get();
		
		admin.setNomeCompleto(dados.nomeCompleto());
		admin.setEmail(dados.email());
		admin.setSenha(dados.senha());
		admin.setTelefone(dados.telefone());
		admin.setPermissao(dados.permissao());
		
		Adm saved = admRepository.save(admin);
		
		return new DadosDetalhamentoAdm(saved);
	}

	public DadosDetalhamentoAdm excluirAdm(String id) throws Exception {
		Optional<Adm> procurado = admRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		
		Adm admin = procurado.get();
		admin.setAtivo(false);
		Adm saved = admRepository.save(admin);
		
		return new DadosDetalhamentoAdm(saved);
		
	}

}
