// src/modules/documents/components/DocumentCard.tsx
import React from 'react';
import { DocumentDto } from '../types';

interface Props {
  document: DocumentDto;
  onClick: (doc: DocumentDto) => void;
}

const DocumentCard: React.FC<Props> = ({ document, onClick }) => {
  return (
    <div
      className="border p-4 rounded shadow hover:bg-gray-50 cursor-pointer transition-colors"
      onClick={() => onClick(document)}
    >
      <h3 className="font-bold text-blue-900">{document.filename}</h3>
      <p className="text-sm text-gray-500">Caricato da: {document.uploadedBy}</p>
      <p className="text-sm text-gray-400">{new Date(document.createdAt).toLocaleString()}</p>
    </div>
  );
};

export default DocumentCard;
