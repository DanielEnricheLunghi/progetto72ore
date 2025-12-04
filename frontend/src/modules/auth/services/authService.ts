import type {RegisterRequest} from "../../../core/types/RegisterRequest.ts";

const API_URL = "http://localhost:8080/api/auth";

export async function login(email: string, password: string) {
    const res = await fetch(`${API_URL}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
    });

    if (!res.ok) {
        throw new Error("Credenziali errate");
    }

    return res.json();
}

export async function register(data: RegisterRequest) {
    const res = await fetch(`${API_URL}/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
    });

    if (!res.ok) {
        throw new Error("Registrazione non valida");
    }

    return res.json();
}
