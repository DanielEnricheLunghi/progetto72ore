import { useState, useCallback, useEffect } from 'react';
 import { DocumentDto, DocumentVersionDto, AuditDto } from '../types';
 import DocumentsService from '../services/documents.service';
 import S3Service from '../services/s3.service';

 export const useDocuments = (condominiumId: number) => {
   const [documents, setDocuments] = useState<DocumentDto[]>([]);
   const [selectedDocument, setSelectedDocument] = useState<DocumentDto | null>(null);
   const [versions, setVersions] = useState<DocumentVersionDto[]>([]);
   const [audits, setAudits] = useState<AuditDto[]>([]);
   const [loading, setLoading] = useState(false);

   // Carica tutti i documenti
   const loadDocuments = useCallback(async () => {
     setLoading(true);
     try {
       const docs = await DocumentsService.getDocuments(condominiumId);
       setDocuments(docs);
     } finally {
       setLoading(false);
     }
   }, [condominiumId]);

   // Seleziona un documento
   const selectDocument = useCallback(async (doc: DocumentDto) => {
     setSelectedDocument(doc);
     const docVersions = await DocumentsService.getVersions(doc.id);
     setVersions(docVersions);
     const auditLog = await DocumentsService.getAudit(doc.id);
     setAudits(auditLog);
   }, []);

   // Upload file
   const uploadDocument = useCallback(async (file: File) => {
     const uploadedFile = await S3Service.uploadFile(file, condominiumId);
     const doc = await DocumentsService.createDocument({
       filename: uploadedFile.filename,
       s3Path: uploadedFile.s3Path,
       mimeType: uploadedFile.mimeType,
       sizeBytes: uploadedFile.size,
       condominiumId,
     });
     setDocuments(prev => [...prev, doc]);
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
     reloadDocuments: loadDocuments,
   };
 };
