import { create } from 'zustand'

const useCoinStore = create((set) => ({
  market: "",
  price: 0,

  setMarket: (market) => set({ market }),
  setPrice: (price) => set({ price })
}))

export default useCoinStore