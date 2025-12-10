import { Navigate } from "react-router-dom";
import { ReactNode } from "react";
import { useAuth } from "@/core/components/AuthContext";

interface ProtectedRouteProps {
  roles: string[];
  children: ReactNode;
}

export default function ProtectedRoute({ roles, children }: ProtectedRouteProps) {
  const { user } = useAuth();
  const userRoles = user?.roles || [];

  if (!user) {
    // Utente non loggato → redirect al login
    return <Navigate to="/login" replace />;
  }

  const hasPermission = userRoles.some((role) => roles.includes(role));

  if (!hasPermission) {
    // Utente loggato ma senza permessi → redirect a pagina dedicata
    return <Navigate to="/unauthorized" replace />;
  }

  // Utente autorizzato → mostra la pagina
  return <>{children}</>;
}