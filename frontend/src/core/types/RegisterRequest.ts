export interface RegisterRequest {
    email: string;
    username: string;
    password: string;
    role: "USER" | "ADMIN";
    vat?: string; // Partita IVA (solo se ADMIN)
}
