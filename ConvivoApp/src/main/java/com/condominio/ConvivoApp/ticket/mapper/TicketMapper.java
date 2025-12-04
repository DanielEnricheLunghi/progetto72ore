package com.condominio.ConvivoApp.ticket.mapper;

import com.condominio.ConvivoApp.ticket.dto.TicketDTO;
import com.condominio.ConvivoApp.ticket.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketDTO toDto(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .priority(ticket.getPriority())
                .createdBy(ticket.getCreatedBy())
                .condominiumId(ticket.getCondominiumId())
                .unitId(ticket.getUnitId())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .build();
    }

    public Ticket toEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setTitle(ticketDTO.getTitle());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setStatus(ticketDTO.getStatus());
        ticket.setPriority(ticketDTO.getPriority());
        ticket.setCreatedBy(ticketDTO.getCreatedBy());
        ticket.setCondominiumId(ticketDTO.getCondominiumId());
        ticket.setUnitId(ticketDTO.getUnitId());
        ticket.setCreatedAt(ticketDTO.getCreatedAt());
        ticket.setUpdatedAt(ticketDTO.getUpdatedAt());
        return ticket;
    }
}


