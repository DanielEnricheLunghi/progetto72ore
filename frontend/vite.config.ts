import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc"; // ✅ plugin SWC
import path from "path";

export default defineConfig({
    plugins: [react()],
    resolve: {
        alias: {
            "@": path.resolve(__dirname, "./src"),
            "@core": path.resolve(__dirname, "./src/core"),
            "@modules": path.resolve(__dirname, "./src/modules"),
            "@components": path.resolve(__dirname, "./src/components"),
            "@assets": path.resolve(__dirname, "./src/assets"),
            "@state": path.resolve(__dirname, "./src/state")
        }
    }
});
