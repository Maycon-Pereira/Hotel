package com.project.hotel.domain.quarto;

import com.project.hotel.entity.Quarto;

public record DadosDetalhamentoQuarto(String id, String numero, String tipo, int capacidade, double precoPorNoite, boolean disponivel) {

    public DadosDetalhamentoQuarto(Quarto quarto) {
        this(quarto.getId(), quarto.getNumero(), quarto.getTipo(), quarto.getCapacidade(), quarto.getPrecoPorNoite(), quarto.isDisponivel());
    }
    
}
