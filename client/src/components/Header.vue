<script setup lang="ts">
import { onMounted, computed, ComputedRef } from 'vue';
import { useAuthStore } from '../store/auth';
import RouterBar from './RouterBar.vue';
import Logo from './Logo.vue';
import { useOwnerStore } from '../store/owner';

import UserIcon from './UserIcon.vue';
import LogOutIcon from './LogOutIcon.vue';

const authStore = useAuthStore();
const ownerStore = useOwnerStore();
let emailName: ComputedRef<String>;
let firstName: ComputedRef<string | undefined>;

onMounted(() => {
  emailName = computed((): String => authStore.getEmail);
  firstName = computed(() => ownerStore.getFirstName);
});
</script>

<template>
    <header class="header-wrapper">
      <div class="header-container">
        <div class="header-panel">
          <div class="header-panel__logo">
            <Logo />
          </div>
        </div>
        <RouterBar v-if="authStore.isLoggedIn" />
        <div class="user-panel" v-if="authStore.isLoggedIn">
          <div class="user-panel__icon">
            <UserIcon />
          </div>
          <h2 v-if="firstName">Welcome, {{ firstName }}</h2>
          <h2 v-else-if="emailName">Welcome, {{ emailName.split('@')[0] }}</h2>
          <h2 v-else="firstName">Welcome, User</h2>
          <div @click="authStore.logOut" class="user-panel__icon">
            <LogOutIcon />
          </div>
        </div>
      </div>
    </header>  
  </template>
  
<script lang="ts">
  export default {
    components: {
      RouterBar
    },
  };
</script>
  
  <style scoped>
  .header-wrapper {
    width: 100%;
    background-color: #E2F4F7;
    border: 0px solid rgba(0, 0, 0, 0.5);
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 4px 4px rgba(0, 0, 0, 0.25);
  }

  .header-container {
    margin: 0 auto;
    max-width: 1440px;
    padding: 20px 65px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .header-panel {
    display: flex;
    gap: 80px;
    align-items: center;
  }

  .header-panel__logo {
    height: 55px;
    will-change: filter;
    transition: filter 300ms;
    cursor: pointer;
  }

  .header-panel__logo:hover {
    filter: drop-shadow(0 0 2em #646cffaa);
  }

  .user-panel {
    display: flex;
    align-items: center;
    gap: 14px;
  }

  .user-panel__icon {
    width: 35px;
    height: 35px;
    cursor: pointer;
  }
  </style>
  