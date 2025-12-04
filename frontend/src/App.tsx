// src/App.tsx

import { useState } from 'react';                     // useState per gestire stati locali
import reactLogo from './assets/react.svg';           // logo React
import viteLogo from '/vite.svg';                     // logo Vite
import './App.css';                                   // import dei CSS
import UserForm from "./components/UserForm";         // import del form utente

function App() {
    // Stato di esempio per il contatore
    const [count, setCount] = useState(0);

    return (
        <>
            {/* Sezione loghi */}
            <div>
                <a href="https://vite.dev" target="_blank">
                    <img src={viteLogo} className="logo" alt="Vite logo" />
                </a>
                <a href="https://react.dev" target="_blank">
                    <img src={reactLogo} className="logo react" alt="React logo" />
                </a>
            </div>

            {/* Titolo principale */}
            <h1>Vite + React</h1>

            {/* Card con contatore di esempio */}
            <div className="card">
                <button onClick={() => setCount((count) => count + 1)}>
                    count is {count}  {/* Mostra il valore corrente del contatore */}
                </button>
                <p>
                    Edit <code>src/App.tsx</code> and save to test HMR
                </p>
            </div>

            {/* Form utente importato */}
            <div className="user-form-container">
                <h2>Gestione Utenti</h2>
                <UserForm />   {/* Renderizza il form per creare utenti */}
            </div>

            {/* Link utili */}
            <p className="read-the-docs">
                Click on the Vite and React logos to learn more
            </p>
        </>
    );
}

export default App;
