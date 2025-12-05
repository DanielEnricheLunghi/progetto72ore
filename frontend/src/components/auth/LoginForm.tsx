/**
 * LoginForm.tsx
 * ----------------
 * Layout a due colonne: sinistra con form, destra con messaggio di benvenuto.
 * Sfondo gradiente solo sul form, card con bordi arrotondati e pulsante rosa.
 * Icone nei campi input, toggle visibilità password.
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
    <div className="min-h-screen flex items-center justify-center px-4">
      <div className="w-full max-w-4xl flex overflow-hidden rounded-[15px] shadow-2xl">
        {/* Sezione sinistra: form con gradiente */}
        <div className="w-1/2 p-8 flex flex-col justify-center bg-gradient-to-r from-pink-500 via-orange-400 to-yellow-300 text-white">
          <h2 className="text-3xl font-bold mb-2">Hello!</h2>
          <p className="text-lg mb-6">Sign into Your account</p>

          {error && <p className="text-sm text-red-100 mb-4">{error}</p>}

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
                className="w-full pl-10 pr-4 py-3 rounded-md bg-white/20 text-white placeholder-white focus:ring-2 focus:ring-white focus:outline-none"
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
                className="w-full pl-10 pr-10 py-3 rounded-md bg-white/20 text-white placeholder-white focus:ring-2 focus:ring-white focus:outline-none"
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
              className="w-full py-3 bg-white text-pink-500 font-semibold rounded-md hover:bg-pink-100 transition"
            >
              SIGN IN
            </button>

            {/* Link */}
            <p className="text-sm text-center mt-2">
              Don't have an account?{" "}
              <a href="#" className="underline hover:text-white/80">
                Create
              </a>
            </p>
          </form>
        </div>

        {/* Sezione destra: messaggio */}
        <div className="w-1/2 bg-pink-100 p-8 flex flex-col justify-center items-center text-center">
          <h2 className="text-3xl font-bold text-pink-600 mb-4">Welcome Back!</h2>
          <p className="text-gray-700 text-sm leading-relaxed">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt ut labore et dolore magna aliqua.
          </p>
        </div>
      </div>
    </div>
  );
}