import axios from 'axios';
import { DocumentDto, DocumentVersionDto, AuditDto } from '../types';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/documents';

class DocumentsService {
  static async getDocuments(condominiumId: number): Promise<DocumentDto[]> {
    const response = await axios.get(`${API_URL}/condo/${condominiumId}`);
    return response.data;
  }

  static async getDocument(documentId: number): Promise<DocumentDto> {
    const response = await axios.get(`${API_URL}/${documentId}`);
    return response.data;
  }

  static async getVersions(documentId: number): Promise<DocumentVersionDto[]> {
    const response = await axios.get(`${API_URL}/${documentId}/versions`);
    return response.data;
  }

  static async getAudit(documentId: number): Promise<AuditDto[]> {
    const response = await axios.get(`${API_URL}/${documentId}/audit`);
    return response.data;
  }

  static async createDocument(data: Partial<DocumentDto>): Promise<DocumentDto> {
    const response = await axios.post(`${API_URL}`, data);
    return response.data;
  }
}

export default DocumentsService;
