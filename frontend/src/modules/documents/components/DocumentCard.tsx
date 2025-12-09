import React from 'react';
import { DocumentDto } from '../types';

interface Props {
  document: DocumentDto;
  onClick: (doc: DocumentDto) => void;
}

const DocumentCard: React.FC<Props> = ({ document, onClick }) => {
  return (
    <div
      className="border p-4 rounded shadow hover:bg-gray-50 cursor-pointer"
      onClick={() => onClick(document)}
    >
      <h3 className="font-bold">{document.filename}</h3>
      <p className="text-sm text-gray-500">Caricato da: {document.uploadedBy}</p>
      <p className="text-sm text-gray-400">{new Date(document.createdAt).toLocaleString()}</p>
    </div>
  );
};

export default DocumentCard;
