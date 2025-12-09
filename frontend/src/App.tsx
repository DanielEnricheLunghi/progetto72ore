/**
 * App.tsx
 * ----------------
 * Componente principale dell'applicazione.
 * Gestisce il routing tra le varie pagine (Welcome, Login, Register).
 */

import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "@modules/auth/pages/LoginPage";       // Pagina di login
import RegisterPage from "@modules/auth/pages/RegisterPage"; // Pagina di registrazione
import WelcomePage from "@modules/welcome/pages/WelcomePage"; // Pagina di benvenuto

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Route principale: homepage */}
        <Route path="/" element={<WelcomePage />} />

        {/* Route per login */}
        <Route path="/login" element={<LoginPage />} />

        {/* Route per registrazione */}
        <Route path="/register" element={<RegisterPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;