import React from 'react';
import { TicketDto } from '../types';

interface Props {
  ticket: TicketDto;
  onClick?: (ticket: TicketDto) => void;
}

const TicketCard: React.FC<Props> = ({ ticket, onClick }) => {
  return (
    <div
      className="border p-4 rounded shadow hover:bg-gray-50 cursor-pointer"
      onClick={() => onClick && onClick(ticket)}
    >
      <h3 className="font-bold">{ticket.title}</h3>
      <p className="text-sm text-gray-600">{ticket.description}</p>
      <p className="text-xs text-gray-400 mt-1">Stato: {ticket.status}</p>
      <p className="text-xs text-gray-400">Priorità: {ticket.priority}</p>
    </div>
  );
};

export default TicketCard;
