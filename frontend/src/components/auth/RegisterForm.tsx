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

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
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
    <div className="min-h-screen flex items-start justify-center px-4 py-4">
      <div className="w-full max-w-[1400px] mx-auto rounded-[15px] shadow-2xl
                      bg-gradient-to-r from-pink-500 via-orange-400 to-yellow-300
                      text-white p-6">
        {/* Messaggio in alto */}
        <div className="text-center mb-6">
          <h2 className="text-3xl font-bold mb-2">Create your account</h2>
          <p className="text-sm text-white/90">
            Inserisci i tuoi dati per registrarti e iniziare a gestire il tuo condominio con ConvivoApp.
          </p>
        </div>

        {error && <p className="text-sm text-red-100 mb-4">{error}</p>}
        {success && <p className="text-sm text-green-100 mb-4">{success}</p>}

        <form onSubmit={handleSubmit} className="space-y-4">
          {/* Username + Email */}
          <div className="grid grid-cols-2 gap-6">
            <input type="text" name="username" placeholder="Username" value={form.username} onChange={handleChange} required className={input} />
            <input type="email" name="email" placeholder="E-mail" value={form.email} onChange={handleChange} required className={input} />
          </div>

          {/* Display Name + Password */}
          <div className="grid grid-cols-2 gap-6">
            <input type="text" name="displayName" placeholder="Nome visualizzato" value={form.displayName} onChange={handleChange} required className={input} />
            <div className="relative">
              <input type={showPassword ? "text" : "password"} name="password" placeholder="Password" value={form.password} onChange={handleChange} required className={`${input} pr-10`} />
              <button type="button" onClick={() => setShowPassword(!showPassword)} className="absolute right-3 top-1/2 transform -translate-y-1/2 text-white">
                {showPassword ? <FiEyeOff /> : <FiEye />}
              </button>
            </div>
          </div>

          {/* Ruolo + Partita IVA */}
          <div className="grid grid-cols-2 gap-6">
            <select name="role" value={form.role} onChange={handleChange} className={input}>
              <option value="USER">Utente</option>
              <option value="ADMIN">Amministratore</option>
            </select>
            {form.role === "ADMIN" && (
              <input type="text" name="vat" placeholder="Partita IVA" value={form.vat} onChange={handleChange} required className={input} />
            )}
          </div>

          {/* Condominio */}
          <div className="grid grid-cols-2 gap-6">
            <input type="text" name="condominiumName" placeholder="Nome condominio" value={form.condominiumName} onChange={handleChange} className={input} />
            <input type="text" name="condominiumAddress" placeholder="Indirizzo condominio" value={form.condominiumAddress} onChange={handleChange} className={input} />
            <input type="text" name="condominiumCity" placeholder="Città" value={form.condominiumCity} onChange={handleChange} className={input} />
            <input type="text" name="condominiumPostcode" placeholder="CAP" value={form.condominiumPostcode} onChange={handleChange} className={input} />
          </div>
          <textarea name="condominiumMetadata" placeholder="Metadati condominio" value={form.condominiumMetadata} onChange={handleChange} className={`${input} h-20`} />

          {/* Membership */}
          <div className="grid grid-cols-2 gap-6">
            <input type="number" name="unitId" placeholder="ID unità immobiliare" value={form.unitId ?? ""} onChange={handleChange} className={input} />
            <input type="text" name="roleInUnit" placeholder="Ruolo nell'unità" value={form.roleInUnit} onChange={handleChange} className={input} />
          </div>
          <textarea name="membershipMetadata" placeholder="Metadati membership" value={form.membershipMetadata} onChange={handleChange} className={`${input} h-20`} />

          <button type="submit" className="w-full py-2 bg-white text-pink-500 font-semibold rounded-md hover:bg-pink-100 transition">
            SIGN UP
          </button>
        </form>
      </div>
    </div>
  );
}

const input = "w-full py-2 px-4 rounded-md bg-white/20 text-white placeholder-white focus:ring-2 focus:ring-white focus:outline-none";