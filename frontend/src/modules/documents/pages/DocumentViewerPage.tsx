// src/modules/documents/pages/DocumentViewerPage.tsx
import React, { useEffect, useState } from 'react';
import { useDocuments } from '@modules/documents/hooks/useDocuments';
import DocumentVersionList from '@modules/documents/components/DocumentVersionList';
import AuditTimeline from '@modules/documents/components/AuditTimeline';
import { DocumentDto } from '@modules/documents/types';

interface Props {
  condominiumId: number;
  documentId: number;
}

const DocumentViewerPage: React.FC<Props> = ({ condominiumId, documentId }) => {
  const { selectedDocument, versions, audits, selectDocument, loading } = useDocuments(condominiumId);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    setError(null);
    if (!selectedDocument || selectedDocument.id !== documentId) {
      selectDocument({ id: documentId } as DocumentDto).catch(() => {
        setError('Errore nel caricamento del documento.');
      });
    }
  }, [documentId, selectDocument, selectedDocument]);

  if (loading) return <p className="text-center text-blue-700 font-semibold">Caricamento documento...</p>;
  if (error) return <p className="text-center text-red-600 font-semibold">{error}</p>;
  if (!selectedDocument) return <p className="text-center text-gray-700 font-medium">Seleziona un documento...</p>;

  return (
    <div className="min-h-screen flex items-center justify-center bg-blue-100 p-4">
      <div className="bg-white shadow-xl rounded-lg w-full max-w-2xl p-6 flex flex-col space-y-6">
        <h2 className="text-2xl font-bold text-blue-900">{selectedDocument.filename}</h2>

        {/* Preview documento */}
        <div className="w-full h-80 bg-blue-50 border-2 border-blue-300 rounded-lg flex items-center justify-center overflow-hidden">
          {selectedDocument.mimeType.startsWith('image/') ? (
            <img src={selectedDocument.s3Path} alt={selectedDocument.filename} className="max-h-full object-contain" />
          ) : (
            <p className="text-gray-500">Anteprima non disponibile</p>
          )}
        </div>

        {/* Versioni */}
        <section>
          <h3 className="font-semibold text-blue-800 mb-2">Versioni</h3>
          {versions.length > 0 ? <DocumentVersionList versions={versions} /> : <p className="text-gray-500">Nessuna versione disponibile</p>}
        </section>

        {/* Audit */}
        <section>
          <h3 className="font-semibold text-blue-800 mb-2">Audit</h3>
          {audits.length > 0 ? <AuditTimeline audits={audits} /> : <p className="text-gray-500">Nessun log disponibile</p>}
        </section>
      </div>
    </div>
  );
};

export default DocumentViewerPage;
