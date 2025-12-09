import React, { useState } from 'react';
import { useTickets } from '../hooks/useTickets';
import TicketList from '../components/TicketList';
import TicketAssignmentModal from '../components/TicketAssignmentModal';
import { UserDto } from '../types';

interface Props {
  users: UserDto[];
}

const TicketListPage: React.FC<Props> = ({ users }) => {
  const { tickets, loading, assignTicket } = useTickets();
  const [selectedTicket, setSelectedTicket] = useState<number | null>(null);

  if (loading) return <p>Caricamento ticket...</p>;

  return (
    <div className="p-4">
      <TicketList
        tickets={tickets}
        onClickTicket={t => setSelectedTicket(t.id)}
      />
      {selectedTicket && (
        <TicketAssignmentModal
          users={users}
          onAssign={async (userId) => {
            await assignTicket(selectedTicket, userId);
            setSelectedTicket(null);
          }}
          onClose={() => setSelectedTicket(null)}
        />
      )}
    </div>
  );
};

export default TicketListPage;
