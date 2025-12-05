/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html", // sempre
    "./src/**/*.{js,ts,jsx,tsx}", // include tutti i file React/TSX
    "./src/components/**/*.{js,ts,jsx,tsx}", // include i component
    "./modules/**/*.{js,ts,jsx,tsx}", // se usi @modules

  ],
  theme: {
    extend: {},
  },
  plugins: [
    function ({ addComponents }) {
      addComponents({
        ".btn": {
          padding: "0.5rem 1rem",
          borderRadius: "0.375rem",
          fontWeight: "600",
        },
        ".btn-pink": {
          backgroundColor: "#ec4899",
          color: "#fff",
          "&:hover": {
            backgroundColor: "#db2777",
          },
        },
      })
    },
  ],
}