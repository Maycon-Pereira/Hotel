package com.project.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hotel.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, String> {

}
