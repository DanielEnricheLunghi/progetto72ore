/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",


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