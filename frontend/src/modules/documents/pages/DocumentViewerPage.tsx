import React from 'react';
import { useDocuments } from '../hooks/useDocuments';
import DocumentVersionList from '../components/DocumentVersionList';
import AuditTimeline from '../components/AuditTimeline';

interface Props {
  condominiumId: number;
  documentId: number;
}

const DocumentViewerPage: React.FC<Props> = ({ condominiumId, documentId }) => {
  const { selectedDocument, versions, audits, selectDocument } = useDocuments(condominiumId);

  React.useEffect(() => {
    if (selectedDocument?.id !== documentId) {
      selectDocument({ id: documentId } as any); // coerciione con DocumentDto minimale
    }
  }, [documentId, selectDocument, selectedDocument]);

  if (!selectedDocument) return <p>Seleziona un documento...</p>;

  return (
    <div className="p-4 flex flex-col space-y-6">
      <h2 className="text-xl font-bold">{selectedDocument.filename}</h2>
      <section>
        <h3 className="font-semibold mb-2">Versioni</h3>
        <DocumentVersionList versions={versions} />
      </section>
      <section>
        <h3 className="font-semibold mb-2">Audit</h3>
        <AuditTimeline audits={audits} />
      </section>
    </div>
  );
};

export default DocumentViewerPage;
