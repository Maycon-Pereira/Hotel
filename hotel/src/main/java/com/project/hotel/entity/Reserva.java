package com.project.hotel.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "reservas")
@Entity(name = "Reserva")
@Getter
@Setter
public class Reserva {

	@Id
	private String id;
	private String usuarioId;
    private String quartoId;
    private LocalDateTime dataCheckIn;
    private LocalDateTime dataCheckOut;
    private LocalDateTime dataReserva;
    private String metodoPagamento;
    private double valorReserva;
    private boolean disponivel;
    private int capacidade;
    
}
