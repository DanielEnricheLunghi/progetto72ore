package com.condominio.ConvivoApp.ticket.controller;

import com.condominio.ConvivoApp.ticket.dto.TicketDTO;
import com.condominio.ConvivoApp.ticket.entity.Ticket;
import com.condominio.ConvivoApp.ticket.mapper.TicketMapper;
import com.condominio.ConvivoApp.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketMapper ticketMapper;

    @PostMapping
    public ResponseEntity<TicketDTO> openTicket(@RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        Ticket created = ticketService.openTicket(ticket);
        TicketDTO response = ticketMapper.toDto(created);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        List<TicketDTO> dtos = tickets.stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable UUID id) {
        Ticket ticket = ticketService.getTicketById(id);
        TicketDTO dto = ticketMapper.toDto(ticket);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable UUID id, @RequestBody TicketDTO ticketDTO) {
        ticketDTO.setId(id);
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        Ticket updated = ticketService.updateTicket(ticket);
        TicketDTO response = ticketMapper.toDto(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/assign")
    public ResponseEntity<Void> assignTicketToSupplier(@PathVariable UUID id, @RequestParam UUID supplierId, @RequestParam UUID adminUserId) {
        ticketService.assignTicketToSupplier(id, supplierId, adminUserId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Void> closeTicket(@PathVariable UUID id, @RequestParam UUID userId) {
        ticketService.closeTicket(id, userId);
        return ResponseEntity.ok().build();
    }
}