package com.condominio.ConvivoApp.ticket.repository;

import com.condominio.ConvivoApp.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    // Metodi custom se necessari
}