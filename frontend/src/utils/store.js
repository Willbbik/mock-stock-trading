import { create } from 'zustand'

const useStockStore = create((set) => ({
  stockCode: "",
  price: 0,

  setStockCode: (stockCode) => set({ stockCode }),
  setPrice: (price) => set({ price }),
  setStockInfo: (stockCode, price) => set({ stockCode, price })
}))

export default useStockStore