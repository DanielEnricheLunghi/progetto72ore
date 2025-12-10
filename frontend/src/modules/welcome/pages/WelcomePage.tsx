/**
 * WelcomePage.tsx
 * ----------------
 * Componente React con Tailwind che rappresenta la pagina di benvenuto.
 * Include effetto hover di ingrandimento e ombra sui pulsanti.
 */

import { useNavigate } from "react-router-dom";

function WelcomePage() {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gradient-to-br from-[#0a1f3d] to-[#0f2c5c] text-white px-6">
      {/* Titolo principale */}
      <h1 className="text-4xl font-bold mb-4">Benvenuto su ConvivoApp</h1>

      {/* Breve descrizione */}
      <p className="text-lg mb-8 text-gray-300">
        Gestione modulare per comunità condominiali
      </p>

      {/* Sezione pulsanti */}
      <div className="flex space-x-4">
        <button
          className="bg-blue-600 hover:bg-blue-800 hover:scale-105 hover:shadow-lg transform transition duration-300 text-white font-semibold py-2 px-6 rounded"
          onClick={() => navigate("/login")}
        >
          Accedi
        </button>
        <button
          className="bg-blue-600 hover:bg-blue-800 hover:scale-105 hover:shadow-lg transform transition duration-300 text-white font-semibold py-2 px-6 rounded"
          onClick={() => navigate("/register")}
        >
          Registrati
        </button>
      </div>
    </div>
  );
}

export default WelcomePage;