// src/modules/documents/pages/DocumentsListPage.tsx
import React, { useState } from 'react';
import { useDocuments } from '@modules/documents/hooks/useDocuments';
import DocumentCard from '@modules/documents/components/DocumentCard';
import { DocumentDto } from '@modules/documents/types';
import { useNavigate } from 'react-router-dom';

interface Props {
  condominiumId: number;
}

const DocumentsListPage: React.FC<Props> = ({ condominiumId }) => {
  const { documents, selectDocument, loading } = useDocuments(condominiumId);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const handleSelect = async (doc: DocumentDto) => {
    try {
      await selectDocument(doc);
      navigate(`/documents/view/${doc.id}`);
    } catch {
      setError('Errore durante il caricamento del documento.');
    }
  };

  return (
    <div className="min-h-screen bg-blue-100 p-4 flex flex-col items-center">
      <div className="w-full max-w-3xl flex flex-col sm:flex-row justify-between items-center mb-6 gap-4">
        <h1 className="text-2xl font-bold text-blue-900">Documenti del Condominio</h1>
        <button
          onClick={() => navigate(`/documents/upload`)}
          className="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded shadow"
        >
          Carica Documento
        </button>
      </div>

      {loading ? (
        <p className="text-blue-700 font-semibold">Caricamento documenti...</p>
      ) : error ? (
        <p className="text-red-600 font-semibold">{error}</p>
      ) : documents.length === 0 ? (
        <p className="text-gray-700 font-medium">Nessun documento disponibile.</p>
      ) : (
        <div className="grid gap-4 w-full grid-cols-1 sm:grid-cols-2 lg:grid-cols-3">
          {documents.map((doc) => (
            <DocumentCard key={doc.id} document={doc} onClick={handleSelect} />
          ))}
        </div>
      )}
    </div>
  );
};

export default DocumentsListPage;
