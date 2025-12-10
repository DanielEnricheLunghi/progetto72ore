// src/modules/documents/pages/UploadDocumentPage.tsx
import React, { useState } from 'react';
import { useDocuments } from '@modules/documents/hooks/useDocuments';
import FileUploader from '@modules/documents/components/FileUploader';

interface Props {
  condominiumId: number;
}

const UploadDocumentPage: React.FC<Props> = ({ condominiumId }) => {
  const { uploadDocument } = useDocuments(condominiumId);
  const [uploading, setUploading] = useState(false);
  const [message, setMessage] = useState<{ type: 'success' | 'error'; text: string } | null>(null);

  const handleUpload = async (file: File) => {
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

        <div className="w-32 h-32 bg-blue-50 border-2 border-blue-300 rounded-lg flex items-center justify-center">
          <img src="/logo.png" alt="Logo Condominio" className="w-20 h-20 object-contain" />
        </div>

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
