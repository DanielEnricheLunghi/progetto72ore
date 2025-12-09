import { useState, useEffect, useCallback } from 'react';
import { TicketDto } from '../types';
import TicketService from '../services/ticket.service';

export const useTickets = () => {
  const [tickets, setTickets] = useState<TicketDto[]>([]);
  const [loading, setLoading] = useState(false);

  const loadTickets = useCallback(async () => {
    setLoading(true);
    try {
      const data = await TicketService.getTickets();
      setTickets(data);
    } finally {
      setLoading(false);
    }
  }, []);

  const assignTicket = useCallback(async (ticketId: number, userId: string) => {
    await TicketService.assignTicket(ticketId, userId);
    await loadTickets();
  }, [loadTickets]);

  useEffect(() => {
    loadTickets();
  }, [loadTickets]);

  return {
    tickets,
    loading,
    loadTickets,
    assignTicket,
  };
};
