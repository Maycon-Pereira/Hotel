package com.project.hotel.controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.hotel.domain.adm.DadosAtualizacaoAdm;
import com.project.hotel.domain.adm.DadosCadastroAdm;
import com.project.hotel.domain.adm.DadosDetalhamentoAdm;
import com.project.hotel.service.AdmService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("admin")
public class AdmController {

	@Autowired
	private AdmService admService;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoAdm> cadastrar(@RequestBody @Valid DadosCadastroAdm dados,
			UriComponentsBuilder uriBuilder) throws Exception {

		DadosDetalhamentoAdm dadosDetalhamentoAdm = admService.criarAdmin(dados);

		var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(dadosDetalhamentoAdm.id()).toUri();

		return ResponseEntity.created(uri).body(dadosDetalhamentoAdm);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoAdm> detalhar(@PathVariable String id) throws Exception {

		DadosDetalhamentoAdm response = admService.detalharAdm(id);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoAdm> atualizar(@PathVariable String id,
			@RequestBody @Valid DadosAtualizacaoAdm dados) throws Exception {

		DadosDetalhamentoAdm response = admService.atualizarAdmin(id, dados);
		if (response == null) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoAdm> excluir(@PathVariable String id) throws Exception {

		admService.excluirAdm(id);

		return ResponseEntity.noContent().build();
	}


}
