// src/modules/documents/components/DocumentVersionList.tsx
import React from 'react';
import { DocumentVersionDto } from '../types';

interface Props {
  versions: DocumentVersionDto[];
}

const DocumentVersionList: React.FC<Props> = ({ versions }) => {
  return (
    <ul className="space-y-2">
      {versions.map(version => (
        <li key={version.id} className="p-2 border rounded flex justify-between">
          <span>Versione {version.versionNumber}</span>
          <span className="text-gray-500">{new Date(version.createdAt).toLocaleString()}</span>
        </li>
      ))}
    </ul>
  );
};

export default DocumentVersionList;
