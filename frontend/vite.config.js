import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path';

// https://vite.dev/config/
export default defineConfig(() => {

  return {
    plugins: [react()],
    resolve: {
      alias: {
        '@api': path.resolve(__dirname, './src/api'),
        '@assets': path.resolve(__dirname, './src/assets'),
        '@components': path.resolve(__dirname, './src/components'),
        '@pages': path.resolve(__dirname, './src/pages'),
        '@utils': path.resolve(__dirname, './src/utils')
      },
    },
    server: {
      port: 3000
    }
  }
})