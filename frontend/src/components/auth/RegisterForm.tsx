import { useState } from "react";
import { register } from "../../modules/auth/services/authService";
import type { RegisterRequest } from "../../core/types/RegisterRequest";

export default function RegisterForm() {
  const [form, setForm] = useState<RegisterRequest>({
    email: "",
    username: "",
    password: "",
    displayName: "",
    role: "USER",
    vat: ""
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    setSuccess("");
    setLoading(true);

    try {
      if (form.role === "ADMIN" && (!form.vat || form.vat.trim() === "")) {
        setError("La partita IVA è obbligatoria per gli amministratori.");
        setLoading(false);
        return;
      }

      const payload: RegisterRequest = {
        email: form.email,
        username: form.username,
        password: form.password,
        displayName: form.displayName,
        role: form.role,
        vat: form.role === "ADMIN" ? form.vat : undefined
      };

      await register(payload);
      setSuccess("Registrazione avvenuta con successo!");
    } catch (err: any) {
      console.error("Errore registrazione:", err);
      if (err.response?.data?.message) {
        setError(err.response.data.message);
      } else {
        setError("Errore nella registrazione. Controlla i campi.");
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{ maxWidth: 400, margin: "0 auto" }}>
      <h2>Registrazione</h2>

      <label>Email</label>
      <input
        type="email"
        name="email"
        value={form.email}
        onChange={handleChange}
        required
      />

      <label>Username</label>
      <input
        type="text"
        name="username"
        value={form.username}
        onChange={handleChange}
        required
      />

      <label>Nome visualizzato</label>
      <input
        type="text"
        name="displayName"
        value={form.displayName}
        onChange={handleChange}
        required
      />

      <label>Password</label>
      <input
        type="password"
        name="password"
        value={form.password}
        onChange={handleChange}
        required
      />

      <label>Ruolo</label>
      <select name="role" value={form.role} onChange={handleChange}>
        <option value="USER">Utente</option>
        <option value="ADMIN">Amministratore</option>
      </select>

      {form.role === "ADMIN" && (
        <>
          <label>Partita IVA</label>
          <input
            type="text"
            name="vat"
            value={form.vat}
            onChange={handleChange}
            required
          />
        </>
      )}

      {error && <p style={{ color: "red" }}>{error}</p>}
      {success && <p style={{ color: "green" }}>{success}</p>}

      <button type="submit" disabled={loading}>
        {loading ? "Caricamento..." : "Registrati"}
      </button>
    </form>
  );
}