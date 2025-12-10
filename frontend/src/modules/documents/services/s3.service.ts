// src/modules/documents/services/s3.service.ts
import axios from 'axios';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

export interface UploadedFile {
  fileName: string;
  s3Path: string;
  mimeType: string;
  sizeBytes: number;
}

class S3Service {
  static async uploadFile(file: File, condominiumId: number): Promise<UploadedFile> {
      if (!condominiumId) {
            throw new Error("condominiumId mancante");
          }

    const formData = new FormData();
    formData.append('file', file);
    formData.append('condoId', String(condominiumId));
    formData.append('documentType', 'GENERIC');

    try {
      const response = await axios.post(`${API_URL}/documents/s3/upload`, formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
        onUploadProgress: (progressEvent) => {
          if (progressEvent.total) {
            console.log(
              'Upload progress:',
              Math.round((progressEvent.loaded * 100) / progressEvent.total)
            );
          }
        }
      });

      return response.data;
    } catch (err) {
      console.error("UPLOAD ERROR:", err.response?.data || err);
      throw err;
    }
  }
}

export default S3Service;
