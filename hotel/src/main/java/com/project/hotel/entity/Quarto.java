package com.project.hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "quartos")
@Entity(name = "Quarto")
@Getter
@Setter
public class Quarto {

	@Id
	private String id;
    private String numero;
    private String tipo;
    private int capacidade;
    private double precoPorNoite;
    private boolean disponivel;
    
}
