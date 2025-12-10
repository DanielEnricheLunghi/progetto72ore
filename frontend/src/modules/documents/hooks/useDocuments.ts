// src/modules/documents/hooks/useDocuments.ts
import { useState, useEffect, useCallback } from 'react';
import { DocumentDto, DocumentVersionDto, AuditDto } from '../types';
import DocumentsService from '../services/documents.service';
import S3Service from '../services/s3.service';

export const useDocuments = (condominiumId: number) => {
  const [documents, setDocuments] = useState<DocumentDto[]>([]);
  const [selectedDocument, setSelectedDocument] = useState<DocumentDto | null>(null);
  const [versions, setVersions] = useState<DocumentVersionDto[]>([]);
  const [audits, setAudits] = useState<AuditDto[]>([]);
  const [loading, setLoading] = useState(false);

  // Carica documenti
  const loadDocuments = useCallback(async () => {
    setLoading(true);
    try {
      const docs = await DocumentsService.getDocuments(condominiumId);
      setDocuments(docs);
    } catch (err) {
      console.error('Errore loadDocuments:', err);
    } finally {
      setLoading(false);
    }
  }, [condominiumId]);

  // Seleziona documento
  const selectDocument = useCallback(async (doc: DocumentDto) => {
    try {
      setLoading(true);
      setSelectedDocument(doc);
      const [docVersions, auditLog] = await Promise.all([
        DocumentsService.getVersions(doc.id),
        DocumentsService.getAudit(doc.id)
      ]);
      setVersions(docVersions);
      setAudits(auditLog);
    } catch (err) {
      console.error('Errore selectDocument:', err);
      setSelectedDocument(null);
      setVersions([]);
      setAudits([]);
      throw err;
    } finally {
      setLoading(false);
    }
  }, []);

  // Upload documento
  const uploadDocument = useCallback(async (file: File) => {
    try {
      setLoading(true);
      const uploadedFile = await S3Service.uploadFile(file, condominiumId);
      const doc = await DocumentsService.createDocument({
        filename: uploadedFile.filename,
        s3Path: uploadedFile.s3Path,
        mimeType: uploadedFile.mimeType,
        sizeBytes: uploadedFile.size,
        condominiumId
      });
      setDocuments(prev => [...prev, doc]);
    } catch (err) {
      console.error('Errore uploadDocument:', err);
      throw err;
    } finally {
      setLoading(false);
    }
  }, [condominiumId]);

  useEffect(() => {
    loadDocuments();
  }, [loadDocuments]);

  return {
    documents,
    selectedDocument,
    versions,
    audits,
    loading,
    selectDocument,
    uploadDocument,
    reloadDocuments: loadDocuments
  };
};
