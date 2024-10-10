package com.project.hotel.service;

import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.domain.quarto.DadosAtualizacaoQuarto;
import com.project.hotel.domain.quarto.DadosCriarQuarto;
import com.project.hotel.domain.quarto.DadosDetalhamentoQuarto;
import com.project.hotel.entity.Quarto;
import com.project.hotel.repository.QuartoRepository;

import jakarta.validation.Valid;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public DadosDetalhamentoQuarto criarQuarto(@Valid DadosCriarQuarto dados) {
        Quarto quarto = new Quarto();
        
        quarto.setId(UUID.randomUUID().toString());
        quarto.setNumero(dados.numero());
        quarto.setTipo(dados.tipo());
        quarto.setCapacidade(dados.capacidade());
        quarto.setPrecoPorNoite(dados.precoPorNoite());
        quarto.setDisponivel(dados.disponivel());
        
        Quarto saved = quartoRepository.save(quarto);
        
        return new DadosDetalhamentoQuarto(saved);
    }

    public DadosDetalhamentoQuarto detalharQuarto(String id) throws Exception {
        Optional<Quarto> procurado = quartoRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }
        
        Quarto quarto = procurado.get();
        return new DadosDetalhamentoQuarto(quarto);
    }

    public DadosDetalhamentoQuarto atualizarQuarto(String id, @Valid DadosAtualizacaoQuarto dados) throws Exception {
        Optional<Quarto> procurado = quartoRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }
        
        Quarto quarto = procurado.get();
        
        quarto.setNumero(dados.numero());
        quarto.setTipo(dados.tipo());
        quarto.setCapacidade(dados.capacidade());
        quarto.setPrecoPorNoite(dados.precoPorNoite());
        quarto.setDisponivel(dados.disponivel());
        
        Quarto saved = quartoRepository.save(quarto);
        
        return new DadosDetalhamentoQuarto(saved);
    }

    public DadosDetalhamentoQuarto excluirQuarto(String id) throws Exception {
        Optional<Quarto> procurado = quartoRepository.findById(id);
        if (!procurado.isPresent()) {
            throw new AccountNotFoundException("Id não encontrado na base");
        }
        
        Quarto quarto = procurado.get();
        quarto.setDisponivel(false);
        Quarto saved = quartoRepository.save(quarto);
        
        return new DadosDetalhamentoQuarto(saved);
        
    }
}
