import React, { useEffect, useState } from 'react';
import { useTickets } from '../hooks/useTickets';
import { TicketDto } from '../types';

interface Props {
  ticketId: number;
}

const TicketDetailPage: React.FC<Props> = ({ ticketId }) => {
  const { tickets } = useTickets();
  const [ticket, setTicket] = useState<TicketDto | null>(null);

  useEffect(() => {
    const found = tickets.find(t => t.id === ticketId) || null;
    setTicket(found);
  }, [ticketId, tickets]);

  if (!ticket) return <p>Ticket non trovato.</p>;

  return (
    <div className="p-4 space-y-4">
      <h2 className="text-xl font-bold">{ticket.title}</h2>
      <p>{ticket.description}</p>
      <p className="text-gray-500">Stato: {ticket.status}</p>
      <p className="text-gray-500">Priorità: {ticket.priority}</p>
      {ticket.assignedTo && <p className="text-gray-500">Assegnato a: {ticket.assignedTo.name}</p>}
    </div>
  );
};

export default TicketDetailPage;
