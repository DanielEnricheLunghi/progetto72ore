// src/App.tsx

import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "@modules/auth/pages/LoginPage";
import RegisterPage from "@modules/auth/pages/RegisterPage";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
