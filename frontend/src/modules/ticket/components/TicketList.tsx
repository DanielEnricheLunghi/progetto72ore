import React from 'react';
import { TicketDto } from '../types';
import TicketCard from './TicketCard';

interface Props {
  tickets: TicketDto[];
  onClickTicket?: (ticket: TicketDto) => void;
}

const TicketList: React.FC<Props> = ({ tickets, onClickTicket }) => {
  if (!tickets.length) return <p className="text-gray-400">Nessun ticket trovato.</p>;

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
      {tickets.map(ticket => (
        <TicketCard key={ticket.id} ticket={ticket} onClick={onClickTicket} />
      ))}
    </div>
  );
};

export default TicketList;
