import { getActivePinia } from 'pinia';
import { watchEffect } from 'vue';

export function usePiniaLocalStorage(key: string): void {
  const pinia = getActivePinia();

  if (pinia) {
    const existingAppState = localStorage.getItem(key);

    if (existingAppState) {
      const existingValue = JSON.parse(existingAppState);
      pinia.state.value = { instanceKey: existingValue.instanceKey };
    }

    watchEffect(() => {
      localStorage.setItem(key, JSON.stringify(pinia.state.value));
    });
  }
}