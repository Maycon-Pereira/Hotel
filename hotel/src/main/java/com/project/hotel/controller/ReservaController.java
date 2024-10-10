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

import com.project.hotel.domain.reserva.DadosAtualizacaoReserva;
import com.project.hotel.domain.reserva.DadosCriarReserva;
import com.project.hotel.domain.reserva.DadosDetalhamentoReserva;
import com.project.hotel.service.ReservaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("reserva")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

		@PostMapping
		@Transactional
		public ResponseEntity<DadosDetalhamentoReserva> criarReserva(@RequestBody @Valid DadosCriarReserva dados,
				UriComponentsBuilder uriBuilder) throws Exception {

			DadosDetalhamentoReserva response = reservaService.criarReserva(dados);

			var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(response.id()).toUri();

			return ResponseEntity.created(uri).body(response);
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<DadosDetalhamentoReserva> detalhar(@PathVariable String id) throws Exception {

			DadosDetalhamentoReserva response = reservaService.detalharReserva(id);

			return ResponseEntity.ok(response);
		}
		
		@PutMapping("/{id}")
		@Transactional
		public ResponseEntity<DadosDetalhamentoReserva> atualizar(@PathVariable String id,
				@RequestBody @Valid DadosAtualizacaoReserva dados) throws Exception {

			DadosDetalhamentoReserva response = reservaService.atualizarReserva(id, dados);
			if (response == null) {
				throw new AccountNotFoundException("Id não encontrado na base");
			}
			return ResponseEntity.ok(response);
		}
		
		@DeleteMapping("/{id}")
		@Transactional
		public ResponseEntity<DadosDetalhamentoReserva> excluir(@PathVariable String id) throws Exception {

			reservaService.excluirReserva(id);

			return ResponseEntity.noContent().build();
		}

}
