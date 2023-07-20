<script setup lang="ts">
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import { usePiniaLocalStorage } from './composables'

usePiniaLocalStorage('app-state')
</script>

<template>
  <!-- <HelloWorld msg="Vite + Vue" /> -->
  <Header />
  <router-view v-slot="slotProps">
    <transition name="route" mode="out-in">
      <component :is="slotProps.Component"></component>
    </transition>
  </router-view>
  <Footer />
</template>

<style scoped>

</style>
