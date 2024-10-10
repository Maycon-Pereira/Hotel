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

import com.project.hotel.domain.reserva.DadosDetalhamentoReserva;
import com.project.hotel.domain.reserva.DadosQuartoReservado;
import com.project.hotel.domain.user.DadosAtualizacaoUser;
import com.project.hotel.domain.user.DadosCadastroUser;
import com.project.hotel.domain.user.DadosDetalhamentoUser;
import com.project.hotel.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoUser> cadastrar(@RequestBody @Valid DadosCadastroUser dados,
			UriComponentsBuilder uriBuilder) throws Exception {

		DadosDetalhamentoUser dadosDetalhamentoUser = userService.criarUser(dados);

		var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(dadosDetalhamentoUser.id()).toUri();

		return ResponseEntity.created(uri).body(dadosDetalhamentoUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoUser> detalhar(@PathVariable String id) throws Exception {

		DadosDetalhamentoUser response = userService.detalharUser(id);

		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoUser> atualizar(@PathVariable String id, @RequestBody @Valid DadosAtualizacaoUser dados)
			throws Exception {

		DadosDetalhamentoUser response = userService.atualizarUser(id, dados);
		if (response == null) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoUser> excluir(@PathVariable String id) throws Exception {

		userService.excluirUser(id);

		return ResponseEntity.noContent().build();
	}
	
	
	// Reservar

	@PostMapping("/reserva")
	@Transactional
	public ResponseEntity<DadosDetalhamentoReserva> reservarQuarto(@RequestBody @Valid DadosQuartoReservado reserva) throws Exception {
		
		DadosDetalhamentoReserva response = userService.reservarQuarto(reserva);
		return ResponseEntity.ok(response);
	}
	
}
