export interface RegisterRequest {
  email: string;
  username: string;
  password: string;
  displayName: string;
  role: "USER" | "ADMIN";
  vat?: string;
}