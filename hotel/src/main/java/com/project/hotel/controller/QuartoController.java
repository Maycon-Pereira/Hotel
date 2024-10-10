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

import com.project.hotel.domain.quarto.DadosAtualizacaoQuarto;
import com.project.hotel.domain.quarto.DadosCriarQuarto;
import com.project.hotel.domain.quarto.DadosDetalhamentoQuarto;
import com.project.hotel.service.QuartoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("quarto")
public class QuartoController {

	@Autowired
	private QuartoService quartoService;

		@PostMapping
		@Transactional
		public ResponseEntity<DadosDetalhamentoQuarto> criarQuarto(@RequestBody @Valid DadosCriarQuarto dados,
				UriComponentsBuilder uriBuilder) throws Exception {

			DadosDetalhamentoQuarto response = quartoService.criarQuarto(dados);

			var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(response.id()).toUri();

			return ResponseEntity.created(uri).body(response);
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<DadosDetalhamentoQuarto> detalhar(@PathVariable String id) throws Exception {

			DadosDetalhamentoQuarto response = quartoService.detalharQuarto(id);

			return ResponseEntity.ok(response);
		}
		
		@PutMapping("/{id}")
		@Transactional
		public ResponseEntity<DadosDetalhamentoQuarto> atualizar(@PathVariable String id,
				@RequestBody @Valid DadosAtualizacaoQuarto dados) throws Exception {

			DadosDetalhamentoQuarto response = quartoService.atualizarQuarto(id, dados);
			if (response == null) {
				throw new AccountNotFoundException("Id não encontrado na base");
			}
			return ResponseEntity.ok(response);
		}
		
		@DeleteMapping("/{id}")
		@Transactional
		public ResponseEntity<DadosDetalhamentoQuarto> excluir(@PathVariable String id) throws Exception {

			quartoService.excluirQuarto(id);

			return ResponseEntity.noContent().build();
		}

		
		
		
}
