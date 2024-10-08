package com.project.hotel.service;

import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.domain.user.DadosAtualizacaoUser;
import com.project.hotel.domain.user.DadosCadastroUser;
import com.project.hotel.domain.user.DadosDetalhamentoUser;
import com.project.hotel.entity.User;
import com.project.hotel.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public DadosDetalhamentoUser criarUser(@Valid DadosCadastroUser dados) throws Exception {

        User user = new User();

        user.setNomeCompleto(dados.nomeCompleto());
        user.setEmail(dados.email());
        user.setSenha(dados.senha());
        user.setTelefone(dados.telefone());
        user.setDocumentoIdentidade(dados.documentoIdentidade());
        user.setDataNascimento(dados.dataNascimento());
        user.setEndereco(dados.endereco());

        User saved = userRepository.save(user);

        return new DadosDetalhamentoUser(saved);
    }

    public DadosDetalhamentoUser detalharUser(String id) throws Exception {

        Optional<User> procurado = userRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }

        User user = procurado.get();
        return new DadosDetalhamentoUser(user);
    }

    public DadosDetalhamentoUser atualizarUser(String id, @Valid DadosAtualizacaoUser dados) throws Exception {

        Optional<User> procurado = userRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }

        User user = procurado.get();

        user.setNomeCompleto(dados.nomeCompleto());
        user.setEmail(dados.email());
        user.setSenha(dados.senha());
        user.setTelefone(dados.telefone());
        user.setDocumentoIdentidade(dados.documentoIdentidade());
        user.setDataNascimento(dados.dataNascimento());
        user.setEndereco(dados.endereco());

        User saved = userRepository.save(user);

        return new DadosDetalhamentoUser(saved);
    }

    public DadosDetalhamentoUser excluirUser(String id) throws Exception {

        Optional<User> procurado = userRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }

        User user = procurado.get();
        user.setAtivo(false);
        User saved = userRepository.save(user);

        return new DadosDetalhamentoUser(saved);
    }
}
