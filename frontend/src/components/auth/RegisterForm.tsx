/**
 * RegisterForm.tsx
 * ----------------
 * Form di registrazione con card centrale e stile coerente a Login/Welcome.
 * Sfondo blu scuro sfumato, campi affiancati, pulsante blu con hover e ombra.
 */

import { useState } from "react";
import { register } from "@/modules/auth/services/authService.ts";
import type { RegisterRequest } from "@/core/types/RegisterRequest";
import { FiEye, FiEyeOff } from "react-icons/fi";

export default function RegisterForm() {
  const [form, setForm] = useState<RegisterRequest>({
    username: "",
    email: "",
    password: "",
    displayName: "",
    role: "USER",
    vat: "",
    condominiumName: "",
    condominiumAddress: "",
    condominiumCity: "",
    condominiumPostcode: "",
    condominiumMetadata: "",
    unitId: undefined,
    roleInUnit: "",
    membershipMetadata: ""
  });

  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>
  ) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    if (form.role === "ADMIN" && (!form.vat || form.vat.trim() === "")) {
      setError("La partita IVA è obbligatoria per gli amministratori.");
      return;
    }

    try {
      await register(form);
      setSuccess("Registrazione completata!");
    } catch (err) {
      setError(err instanceof Error ? err.message : "Errore nella registrazione.");
    }
  };

  return (
    <div className="min-h-screen w-full flex items-center justify-center bg-gradient-to-br from-[#0a1f3d] to-[#0f2c5c] px-6 py-10 text-white">
      <div className="w-full max-w-[1600px] rounded-xl shadow-2xl bg-white/5 backdrop-blur-md p-10">
        {/* Intestazione interna */}
        <div className="text-left mb-6">
          <h2 className="text-3xl font-bold mb-2">Create your account</h2>
          <p className="text-sm text-gray-200">
            Inserisci i tuoi dati per registrarti e iniziare a gestire il tuo condominio con ConvivoApp.
          </p>
        </div>

        {error && <p className="text-sm text-red-200 mb-4">{error}</p>}
        {success && <p className="text-sm text-green-200 mb-4">{success}</p>}

        <form onSubmit={handleSubmit} className="grid grid-cols-2 gap-6">
          {/* Username + Email */}
          <input type="text" name="username" placeholder="Username" value={form.username} onChange={handleChange} required className={input} />
          <input type="email" name="email" placeholder="E-mail" value={form.email} onChange={handleChange} required className={input} />

          {/* Display Name + Password */}
          <input type="text" name="displayName" placeholder="Nome visualizzato" value={form.displayName} onChange={handleChange} required className={input} />
          <div className="relative">
            <input type={showPassword ? "text" : "password"} name="password" placeholder="Password" value={form.password} onChange={handleChange} required className={`${input} pr-10`} />
            <button type="button" onClick={() => setShowPassword(!showPassword)} className="absolute right-3 top-1/2 transform -translate-y-1/2 text-white">
              {showPassword ? <FiEyeOff /> : <FiEye />}
            </button>
          </div>

          {/* Ruolo + Partita IVA */}
          <select name="role" value={form.role} onChange={handleChange} className={input}>
            <option value="USER">Utente</option>
            <option value="ADMIN">Amministratore</option>
          </select>
          {form.role === "ADMIN" && (
            <input type="text" name="vat" placeholder="Partita IVA" value={form.vat} onChange={handleChange} required className={input} />
          )}

          {/* Condominio */}
          <input type="text" name="condominiumName" placeholder="Nome condominio" value={form.condominiumName} onChange={handleChange} className={input} />
          <input type="text" name="condominiumAddress" placeholder="Indirizzo condominio" value={form.condominiumAddress} onChange={handleChange} className={input} />
          <input type="text" name="condominiumCity" placeholder="Città" value={form.condominiumCity} onChange={handleChange} className={input} />
          <input type="text" name="condominiumPostcode" placeholder="CAP" value={form.condominiumPostcode} onChange={handleChange} className={input} />

          <textarea name="condominiumMetadata" placeholder="Metadati condominio" value={form.condominiumMetadata} onChange={handleChange} className={`${input} h-20 col-span-2`} />

          {/* Membership */}
          <input type="number" name="unitId" placeholder="ID unità immobiliare" value={form.unitId ?? ""} onChange={handleChange} className={input} />
          <input type="text" name="roleInUnit" placeholder="Ruolo nell'unità" value={form.roleInUnit} onChange={handleChange} className={input} />

          <textarea name="membershipMetadata" placeholder="Metadati membership" value={form.membershipMetadata} onChange={handleChange} className={`${input} h-20 col-span-2`} />

          {/* Submit */}
          <button type="submit" className="col-span-2 py-3 bg-blue-600 text-white font-semibold rounded-md hover:bg-blue-800 hover:scale-105 hover:shadow-lg transform transition duration-300">
            SIGN UP
          </button>
        </form>
      </div>
    </div>
  );
}

const input =
  "w-full py-2 px-4 rounded-md bg-white/10 text-white placeholder-white focus:ring-2 focus:ring-white focus:outline-none";