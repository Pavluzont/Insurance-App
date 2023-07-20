import { defineStore } from 'pinia'

export const useStoreNameStore = defineStore('storeName', {
  state: () => {
    return {
      counter: 1,
      name: 'Pavel',
    }
  },
  getters: {
    doubleCount(state): number {
      return state.counter * 2
    },
    getName(): string {
      return this.name;
    },
    getCount(): number {
      return this.counter;
    }
  },
  actions: {
    increment() {
      this.counter++
    },
    changeName(name: string) {
      this.name = name
    },
  },
})