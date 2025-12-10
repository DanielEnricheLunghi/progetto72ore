/**
 * LoginForm.tsx
 * ----------------
 * Layout a due colonne con stile identico alla pagina Welcome.
 * Sfondo blu scuro sfumato, testo bianco, pulsanti blu con hover e ombra.
 */

import { useState } from "react";
import { login } from "@/modules/auth/services/authService.ts";
import { FaEnvelope, FaLock } from "react-icons/fa";
import { FiEye, FiEyeOff } from "react-icons/fi";

export default function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [remember, setRemember] = useState(false);
  const [error, setError] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    try {
      const res = await login(email, password);
      console.log("TOKEN:", res.token);
      alert("Login effettuato!");
    } catch (err: unknown) {
      setError(err instanceof Error ? err.message : "Errore sconosciuto");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-[#0a1f3d] to-[#0f2c5c] px-4 text-white">
      <div className="w-full max-w-4xl flex overflow-hidden rounded-xl shadow-2xl bg-white/5 backdrop-blur-md">
        {/* Sezione sinistra: form */}
        <div className="w-1/2 p-8 flex flex-col justify-center">
          <h2 className="text-3xl font-bold mb-2">Welcome Back!</h2>
          <p className="text-lg mb-6 text-gray-200">Sign into your account</p>

          {error && <p className="text-sm text-red-200 mb-4">{error}</p>}

          <form onSubmit={handleSubmit} className="space-y-5">
            {/* Email */}
            <div className="relative">
              <FaEnvelope className="absolute left-3 top-1/2 transform -translate-y-1/2 text-white" />
              <input
                type="email"
                placeholder="E-mail"
                value={email}
                onChange={e => setEmail(e.target.value)}
                required
                className="w-full pl-10 pr-4 py-3 rounded-md bg-white/10 text-white placeholder-white focus:ring-2 focus:ring-white focus:outline-none"
              />
            </div>

            {/* Password */}
            <div className="relative">
              <FaLock className="absolute left-3 top-1/2 transform -translate-y-1/2 text-white" />
              <input
                type={showPassword ? "text" : "password"}
                placeholder="Password"
                value={password}
                onChange={e => setPassword(e.target.value)}
                required
                className="w-full pl-10 pr-10 py-3 rounded-md bg-white/10 text-white placeholder-white focus:ring-2 focus:ring-white focus:outline-none"
              />
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="absolute right-3 top-1/2 transform -translate-y-1/2 text-white"
              >
                {showPassword ? <FiEyeOff /> : <FiEye />}
              </button>
            </div>

            {/* Remember + Forgot */}
            <div className="flex items-center justify-between text-sm text-white">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  checked={remember}
                  onChange={e => setRemember(e.target.checked)}
                  className="accent-white"
                />
                Remember me
              </label>
              <a href="#" className="underline hover:text-white/80">
                Forgot password?
              </a>
            </div>

            {/* Submit */}
            <button
              type="submit"
              className="w-full py-3 bg-blue-600 text-white font-semibold rounded-md hover:bg-blue-800 hover:scale-105 hover:shadow-lg transform transition duration-300"
            >
              SIGN IN
            </button>

            {/* Link */}
            <p className="text-sm text-center mt-2 text-gray-200">
              Don't have an account?{" "}
              <a href="#" className="underline hover:text-white/80">
                Create
              </a>
            </p>
          </form>
        </div>

        {/* Sezione destra: messaggio */}
        <div className="w-1/2 bg-white/10 p-8 flex flex-col justify-center items-center text-center">
          <h2 className="text-3xl font-bold text-white mb-4">ConvivoApp</h2>
          <p className="text-gray-200 text-sm leading-relaxed">
            Gestione modulare per comunità condominiali. Accedi per iniziare!
          </p>
        </div>
      </div>
    </div>
  );
}