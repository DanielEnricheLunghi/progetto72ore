import axios from "axios";

import type {RegisterRequest} from "../../../core/types/RegisterRequest.ts";

const API_URL = "http://localhost:8080/api/auth";

//LOGIN
export async function login(email: string, password: string) {
  try {
    const res = await axios.post(`${API_URL}/login`, { email, password });
    return res.data; // Axios restituisce già i dati JSON
  } catch (error: any) {
    throw new Error(error.response?.data?.message || "Credenziali errate");
  }
}



// REGISTER
export async function register(data: RegisterRequest) {
  try {
    const res = await axios.post(`${API_URL}/register`, data);
    return res.data;
  } catch (error: any) {
    throw new Error(error.response?.data?.message || "Registrazione non valida");
  }
}

