package com.condominio.ConvivoApp.ticket.service;

import com.condominio.ConvivoApp.ticket.entity.Ticket;
import com.condominio.ConvivoApp.ticket.repository.TicketRepository;
import com.condominio.ConvivoApp.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final NotificationService notificationService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, NotificationService notificationService) {
        this.ticketRepository = ticketRepository;
        this.notificationService = notificationService;
    }

    public Ticket openTicket(Ticket ticket) {
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setStatus("OPEN");
        Ticket created = ticketRepository.save(ticket);

        notificationService.sendNotification(
                ticket.getCreatedBy(),
                "TICKET_OPENED",
                "Il ticket '" + created.getTitle() + "' è stato aperto."
        );

        return created;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(UUID id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with id: " + id));
    }

    public Ticket updateTicket(Ticket ticket) {
        ticket.setUpdatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(UUID id) {
        ticketRepository.deleteById(id);
    }

    public void assignTicketToSupplier(UUID ticketId, UUID supplierId, UUID adminUserId) {
        Ticket ticket = getTicketById(ticketId);
        // Logica di assegnazione (es. creazione TicketAssignment)
        // ...

        notificationService.sendNotification(
                adminUserId,
                "TICKET_ASSIGNED",
                "Il ticket " + ticketId + " è stato assegnato al fornitore " + supplierId
        );
    }

    public void closeTicket(UUID ticketId, UUID userId) {
        Ticket ticket = getTicketById(ticketId);
        ticket.setStatus("CLOSED");
        ticket.setUpdatedAt(LocalDateTime.now());
        ticketRepository.save(ticket);

        notificationService.sendNotification(
                userId,
                "TICKET_CLOSED",
                "Il ticket " + ticketId + " è stato chiuso."
        );
    }
}