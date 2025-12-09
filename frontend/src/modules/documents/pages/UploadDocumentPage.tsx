import React from 'react';
import FileUploader from '../components/FileUploader';
import { useDocuments } from '../hooks/useDocuments';

interface Props {
  condominiumId: number;
}

const UploadDocumentPage: React.FC<Props> = ({ condominiumId }) => {
  const { uploadDocument } = useDocuments(condominiumId);

  const handleUpload = async (file: File) => {
    try {
      await uploadDocument(file);
      alert('File caricato con successo!');
    } catch (err) {
      console.error(err);
      alert('Errore durante il caricamento del file.');
    }
  };

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Carica Documento</h2>
      <FileUploader onUpload={handleUpload} />
    </div>
  );
};

export default UploadDocumentPage;
