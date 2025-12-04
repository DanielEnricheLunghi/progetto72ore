package com.condominio.ConvivoApp.ticket.repository;

import com.condominio.ConvivoApp.ticket.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}