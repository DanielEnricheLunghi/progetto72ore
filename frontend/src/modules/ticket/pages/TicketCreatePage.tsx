import React from 'react';
import TicketForm from '@/modules/ticket/components/TicketForm';
import { useTickets } from '@/modules/ticket/hooks/useTickets';
import { useNavigate } from 'react-router-dom';

const TicketCreatePage: React.FC = () => {
  const { createTicket } = useTickets();
  const navigate = useNavigate();

  const handleSubmit = async (payload: any) => {
    const created = await createTicket(payload);
    navigate(`/tickets/${created.id}`);
  };

  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">Crea nuovo ticket</h1>
      <TicketForm onSubmit={handleSubmit} />
    </div>
  );
};

export default TicketCreatePage;
