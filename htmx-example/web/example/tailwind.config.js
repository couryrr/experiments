/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./pages/**/*.{html,nil}", "./components/**/*.{html,nil}"],
  theme: {
    extend:
    {
      fontFamily: {
        'roboto': ['Roboto', 'sans-serif']
      },
    },
  },
  plugins: [],
}

