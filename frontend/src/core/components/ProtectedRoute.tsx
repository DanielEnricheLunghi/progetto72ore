import { Navigate } from "react-router-dom";
import { ReactNode } from "react";
import { useAuth } from "@/core/context/AuthContext"; // esempio: hook che espone user e ruoli

interface ProtectedRouteProps {
  roles: string[];
  children: ReactNode;
}

export default function ProtectedRoute({ roles, children }: ProtectedRouteProps) {
  const { user } = useAuth(); // user arriva dal backend e contiene i ruoli
  const userRoles = user?.roles || []; // array di ruoli es. ["ADMIN", "INQUILINO"]

  if (!user) {
    // Utente non loggato → redirect al login
    return <Navigate to="/login" replace />;
  }

  const hasPermission = userRoles.some((role: string) => roles.includes(role));

  if (!hasPermission) {
    // Utente loggato ma senza permessi → redirect alla home
    return <Navigate to="/" replace />;
  }

  // Utente autorizzato → mostra la pagina
  return <>{children}</>;
}