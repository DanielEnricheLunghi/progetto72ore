import React, { useState } from 'react';
import { UserDto } from '../types';

interface Props {
  users: UserDto[];
  onAssign: (userId: string) => void;
  onClose: () => void;
}

const TicketAssignmentModal: React.FC<Props> = ({ users, onAssign, onClose }) => {
  const [selectedUser, setSelectedUser] = useState<string>('');

  return (
    <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center">
      <div className="bg-white p-6 rounded shadow w-96">
        <h3 className="text-lg font-bold mb-4">Assegna Ticket</h3>
        <select
          className="w-full p-2 border rounded mb-4"
          value={selectedUser}
          onChange={e => setSelectedUser(e.target.value)}
        >
          <option value="">Seleziona utente</option>
          {users.map(u => (
            <option key={u.id} value={u.id}>{u.name}</option>
          ))}
        </select>
        <div className="flex justify-end space-x-2">
          <button
            className="px-4 py-2 bg-gray-200 rounded"
            onClick={onClose}
          >Annulla</button>
          <button
            className="px-4 py-2 bg-blue-600 text-white rounded"
            onClick={() => onAssign(selectedUser)}
            disabled={!selectedUser}
          >Assegna</button>
        </div>
      </div>
    </div>
  );
};

export default TicketAssignmentModal;
