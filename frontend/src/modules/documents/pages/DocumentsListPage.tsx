import axios from 'axios';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/documents/s3';

interface UploadedFile {
  filename: string;
  s3Path: string;
  mimeType: string;
  size: number;
}

class S3Service {
  static async uploadFile(file: File, condominiumId: number): Promise<UploadedFile> {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('condominiumId', String(condominiumId));

    const response = await axios.post(`${API_URL}/upload`, formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });

    return response.data;
  }
}

export default S3Service;
