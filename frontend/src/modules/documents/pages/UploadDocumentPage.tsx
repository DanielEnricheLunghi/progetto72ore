import React, { useState } from 'react';
import { useDocuments } from '@modules/documents/hooks/useDocuments';
import FileUploader from '@modules/documents/components/FileUploader';

const UploadDocumentPage: React.FC = () => {
  const [condominiumId, setCondominiumId] = useState<number | null>(null);
  const { uploadDocument } = useDocuments(condominiumId ?? 0);
  const [uploading, setUploading] = useState(false);
  const [message, setMessage] = useState<{ type: 'success' | 'error'; text: string } | null>(null);

  const handleUpload = async (file: File) => {
    if (!condominiumId) {
      setMessage({ type: 'error', text: 'Inserisci un ID condominio valido prima di caricare.' });
      return;
    }

    setUploading(true);
    setMessage(null);
    try {
      await uploadDocument(file);
      setMessage({ type: 'success', text: 'File caricato con successo!' });
    } catch {
      setMessage({ type: 'error', text: 'Errore durante il caricamento del file.' });
    } finally {
      setUploading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-blue-100 p-4">
      <div className="bg-white shadow-xl rounded-lg w-full max-w-md p-6 flex flex-col items-center space-y-6">
        <h2 className="text-2xl font-bold text-blue-900">Carica Documento</h2>

        {/* Campo input per ID condominio */}
        <input
          type="number"
          placeholder="ID Condominio"
          value={condominiumId ?? ''}
          onChange={(e) => setCondominiumId(Number(e.target.value))}
          className="w-full border border-blue-300 rounded px-3 py-2 text-center"
        />

        <FileUploader onUpload={handleUpload} uploading={uploading} />

        {message && (
          <p className={`text-center font-medium ${message.type === 'success' ? 'text-green-600' : 'text-red-600'}`}>
            {message.text}
          </p>
        )}
      </div>
    </div>
  );
};

export default UploadDocumentPage;