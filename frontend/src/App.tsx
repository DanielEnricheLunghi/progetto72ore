/**
 * App.tsx
 * ----------------
 * Componente principale dell'applicazione.
 * Gestisce il routing tra le varie pagine (Welcome, Login, Register).
 */

import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "@modules/auth/pages/LoginPage";       // Pagina di login
import RegisterPage from "@modules/auth/pages/RegisterPage"; // Pagina di registrazione
import WelcomePage from "@modules/welcome/pages/WelcomePage"; // Pagina di benvenuto
// CHAT
import ChatPage from "@modules/chat/pages/ChatPage";
import ConversationPage from "@modules/chat/pages/ConversationPage";
// DOCUMENTS

// DOCUMENTS
import DocumentsListPage from "@modules/documents/pages/DocumentsListPage";
import DocumentViewerPage from "@modules/documents/pages/DocumentViewerPage";
import UploadDocumentPage from "@modules/documents/pages/UploadDocumentPage";

// NOTIFICATIONS

import NotificationPage from "@modules/notifications/pages/NotificationPage";

// TICKETS
import TicketListPage from "@modules/ticket/pages/TicketListPage";
import TicketDetailPage from "@modules/ticket/pages/TicketDetailPage";

// BILLING
import BillingDashboard from "@modules/billing/pages/BillingDashboard";
import BillingLayout from "@modules/billing/layout/BillingLayout";
import ContractsPage from "@modules/billing/pages/ContractsPage";
import PaymentsPage from "@modules/billing/pages/PaymentsPage";
import InvoicesPage from "@modules/billing/pages/InvoicesPage";

import ProtectedRoute from "@core/components/ProtectedRoute";

function App() {
  return (
    <BrowserRouter>


      <Routes>


        <Route path="/" element={<WelcomePage />} />


        <Route path="/login" element={<LoginPage />} />


        <Route path="/register" element={<RegisterPage />} />


     { /*
         <Route
          path="/chat"
          element={
          <ProtectedRoute roles={["ADMIN", "AMMINISTRATORE_CONDOMINIO", "PROPRIETARIO", "INQUILINO", "FORNITORE"]}>
          <ChatPage />
          </ProtectedRoute>
          }
          />

          <Route
          path="/chat/:conversationId"
          element={
          <ProtectedRoute roles={["ADMIN", "AMMINISTRATORE_CONDOMINIO", "PROPRIETARIO", "INQUILINO", "FORNITORE"]}>
          <ConversationPage />
          </ProtectedRoute>
          }
          />


          <Route
          path="/documents"
          element={
          <ProtectedRoute roles={["ADMIN", "AMMINISTRATORE_CONDOMINIO"]}>
          <DocumentsListPage />
          </ProtectedRoute>
          }
          />

          <Route
          path="/documents/view/:id"
          element={
          <ProtectedRoute roles={["ADMIN", "AMMINISTRATORE_CONDOMINIO"]}>
         <DocumentViewerPage />
          </ProtectedRoute>
          }
          />

          <Route
          path="/documents/upload"
           element={
           <ProtectedRoute roles={["ADMIN", "AMMINISTRATORE_CONDOMINIO"]}>
           <UploadDocumentPage />
           </ProtectedRoute>
           }
           />

          path="/notifications"
          element={
            <ProtectedRoute roles={["ADMIN", "AMMINISTRATORE_CONDOMINIO", "PROPRIETARIO", "INQUILINO", "FORNITORE"]}>
              <NotificationPage />
            </ProtectedRoute>
          }
        />


        <Route
          path="/tickets"
          element={
            <ProtectedRoute roles={["ADMIN", "AMMINISTRATORE_CONDOMINIO"]}>
              <TicketListPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/tickets/:ticketId"
          element={
            <ProtectedRoute roles={["ADMIN", "AMMINISTRATORE_CONDOMINIO"]}>
              <TicketDetailPage />
            </ProtectedRoute>
          }
        />
*/}


  <Route path="/chat" element={<ChatPage />} />
  <Route path="/chat/:conversationId" element={<ConversationPage />} />


  <Route path="/documents" element={<DocumentsListPage />} />
  <Route path="/documents/view/:id" element={<DocumentViewerPage />} />
  <Route path="/documents/upload/" element={<UploadDocumentPage />} />


  <Route path="/notifications" element={<NotificationPage />} />


  <Route path="/tickets" element={<TicketListPage />} />
  <Route path="/tickets/:ticketId" element={<TicketDetailPage />} />


  <Route path="/billing" element={<BillingDashboard />} />
  <Route path="/billing" element={<BillingLayout />}>
    <Route path="contracts" element={<ContractsPage />} />
  </Route>
 <Route path="/billing/invoices" element={<InvoicesPage />} />
 <Route path="/billing/payments" element={<PaymentsPage />} />



      </Routes>
    </BrowserRouter>
  );
}

export default App;