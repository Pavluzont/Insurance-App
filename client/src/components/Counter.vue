<template>
  <h1>Name is {{ store.name }}</h1>
  <input type="text" placeholder="New Name" v-model="nameInput" />
  <button @click="handleSubmit">Change Name</button>
  <h2>Clicks count is {{ store.counter }}</h2>
  <button @click="handleClick">Click to raise counter!</button>
</template>

<script lang="ts" setup>
import { useStoreNameStore } from '../store/counter';
import { ref } from 'vue';

const store = useStoreNameStore();
const nameInput = ref("");
const handleSubmit = () => {
  store.changeName(nameInput.value);
  nameInput.value = "";
}
const handleClick = () => {
  store.increment();
}
</script>
