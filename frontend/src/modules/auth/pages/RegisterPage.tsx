// src/pages/RegisterPage.tsx

import RegisterForm from "@components/auth/RegisterForm"; // usa l'alias per il form

export default function RegisterPage() {
    return (
        <div style={{ maxWidth: 500, margin: "0 auto", padding: "2rem" }}>
            <h1>Registrati</h1>
            <RegisterForm />
        </div>
    );
}
