package com.condominio.ConvivoApp.ticket.repository;

import com.condominio.ConvivoApp.ticket.entity.TicketAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketAssignmentRepository extends JpaRepository<TicketAssignment, Long> {
}
