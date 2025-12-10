import React, { useState } from 'react';
import type { Ticket } from '@/types';

interface Props {
  onSubmit: (payload: Partial<Ticket>) => Promise<void> | void;
  initial?: Partial<Ticket>;
}

const TicketForm: React.FC<Props> = ({ onSubmit, initial }) => {
  const [title, setTitle] = useState(initial?.title ?? '');
  const [description, setDescription] = useState(initial?.description ?? '');
  const [priority, setPriority] = useState<Ticket['priority']>(initial?.priority ?? 'MEDIUM');
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    if (!title.trim()) {
      setError('Titolo obbligatorio');
      return;
    }
    setSubmitting(true);
    try {
      await onSubmit({ title, description, priority, status: 'OPEN' });
      setTitle('');
      setDescription('');
      setPriority('MEDIUM');
    } catch (e) {
      setError('Errore durante il salvataggio');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white p-4 rounded shadow">
      {error && <div className="mb-3 text-sm text-red-600">{error}</div>}
      <div className="mb-3">
        <label className="block text-sm font-medium mb-1">Titolo</label>
        <input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          className="w-full border rounded px-3 py-2"
          required
        />
      </div>

      <div className="mb-3">
        <label className="block text-sm font-medium mb-1">Descrizione</label>
        <textarea
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          rows={4}
          className="w-full border rounded px-3 py-2"
        />
      </div>

      <div className="mb-3">
        <label className="block text-sm font-medium mb-1">Priorità</label>
        <select value={priority} onChange={(e) => setPriority(e.target.value as any)} className="w-full border rounded px-3 py-2">
          <option value="LOW">Bassa</option>
          <option value="MEDIUM">Media</option>
          <option value="HIGH">Alta</option>
        </select>
      </div>

      <div className="flex justify-end">
        <button
          type="submit"
          disabled={submitting}
          className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 disabled:opacity-60"
        >
          {submitting ? 'Salvo...' : 'Crea ticket'}
        </button>
      </div>
    </form>
  );
};

export default TicketForm;
