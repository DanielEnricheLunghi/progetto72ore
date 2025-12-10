// src/modules/documents/components/AuditTimeline.tsx
import React from 'react';
import { AuditDto } from '../types';

interface Props {
  audits: AuditDto[];
}

const AuditTimeline: React.FC<Props> = ({ audits }) => {
  return (
    <div className="border-l-2 border-gray-300 pl-4 space-y-4">
      {audits.map(audit => (
        <div key={audit.id} className="relative">
          <span className="absolute -left-3 w-6 h-6 bg-blue-500 rounded-full"></span>
          <p className="text-sm text-gray-700">{audit.action} da {audit.user}</p>
          <p className="text-xs text-gray-400">{new Date(audit.timestamp).toLocaleString()}</p>
        </div>
      ))}
    </div>
  );
};

export default AuditTimeline;
