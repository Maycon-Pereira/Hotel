package com.project.hotel.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hotel.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, String> {

	boolean existsByQuartoIdAndDataCheckInBeforeAndDataCheckOutAfter(String quartoId, LocalDateTime dataCheckOut, LocalDateTime dataCheckIn);

}
