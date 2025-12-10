// src/modules/documents/components/FileUploader.tsx
import React, { useState, useRef } from 'react';
import { FiUploadCloud } from 'react-icons/fi';

interface Props {
  onUpload: (file: File) => void;
  uploading?: boolean;
}

const FileUploader: React.FC<Props> = ({ onUpload, uploading = false }) => {
  const [file, setFile] = useState<File | null>(null);
  const fileInputRef = useRef<HTMLInputElement | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      setFile(e.target.files[0]);
    }
  };

  const handleUpload = () => {
    if (file) {
      onUpload(file);
      setFile(null);
      if (fileInputRef.current) fileInputRef.current.value = '';
    }
  };

  const handleAreaClick = () => {
    if (!uploading && fileInputRef.current) {
      fileInputRef.current.click();
    }
  };

  return (
    <div className="w-full flex flex-col items-center">
      {/* Drag & Drop / Click Area */}
      <div
        className={`w-full p-6 border-2 border-dashed rounded-lg flex flex-col items-center justify-center cursor-pointer hover:border-blue-500 transition-colors text-center bg-blue-50 ${
          uploading ? 'opacity-50 cursor-not-allowed' : 'border-blue-300'
        }`}
        onClick={handleAreaClick}
      >
        <FiUploadCloud size={48} className="text-blue-400 mb-2" />
        <p className="text-blue-600 font-medium">
          {file ? file.name : 'Trascina il file qui o clicca per selezionarlo'}
        </p>
      </div>

      {/* Hidden Input */}
      <input
        type="file"
        ref={fileInputRef}
        className="hidden"
        onChange={handleChange}
        disabled={uploading}
      />

      {/* Upload Button */}
      <button
        onClick={handleUpload}
        disabled={!file || uploading}
        className="mt-4 w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded disabled:opacity-50 transition-colors"
      >
        {uploading ? 'Caricamento...' : 'Carica Documento'}
      </button>
    </div>
  );
};

export default FileUploader;
