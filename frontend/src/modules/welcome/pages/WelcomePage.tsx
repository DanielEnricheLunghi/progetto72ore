/**
 * WelcomePage.tsx
 * ----------------
 * Componente React che rappresenta la pagina di benvenuto.
 * Mostra titolo, descrizione e due pulsanti (Accedi/Registrati).
 * Ogni pulsante porta alla rispettiva pagina tramite React Router.
 */

import { useNavigate } from "react-router-dom"; // Hook per navigare tra le route
import "./WelcomePage.css"; // Import degli stili CSS dedicati

function WelcomePage() {
  const navigate = useNavigate(); // Inizializza la funzione navigate

  return (
    <div className="welcome-container">
      {/* Titolo principale */}
      <h1 className="welcome-title">Benvenuto su ConvivoApp</h1>

      {/* Breve descrizione */}
      <p className="welcome-description">
        Gestione modulare per comunità e veicoli pesanti.
      </p>

      {/* Sezione pulsanti */}
      <div className="button-group">
        <button
          className="welcome-button"
          onClick={() => navigate("/login")}
        >
          Accedi
        </button>
        <button
          className="welcome-button"
          onClick={() => navigate("/register")}
        >
          Registrati
        </button>
      </div>
    </div>
  );
}

export default WelcomePage;