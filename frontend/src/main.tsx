import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { AuthProvider } from "@/core/components/AuthContext";
import './index.css'; // Tailwind
import './global.css'; // Stili personalizzati
import App from './App.tsx';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <AuthProvider> {/* 👈 avvolge App nel contesto */}
        <App />
      </AuthProvider>
  </StrictMode>,
)
