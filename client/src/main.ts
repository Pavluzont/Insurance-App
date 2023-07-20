import { createApp } from 'vue'
import { createPinia } from 'pinia';

import { initializeApp } from "firebase/app";

import App from './App.vue'
import router from './router.ts';

import './style.css'

const firebaseConfig = {
  apiKey: "AIzaSyCticD2fCNTkbtlv-NdinZJkvmVZrpsQyg",
  authDomain: "insurance-ap.firebaseapp.com",
  projectId: "insurance-ap",
  storageBucket: "insurance-ap.appspot.com",
  messagingSenderId: "535447555340",
  appId: "1:535447555340:web:b1317acf158d1f03914d29"
};

initializeApp(firebaseConfig);

const app = createApp(App);

app.use(router);
app.use(createPinia());
app.mount('#app')
