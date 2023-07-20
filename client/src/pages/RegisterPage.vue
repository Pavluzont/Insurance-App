<template>
  <div class="form-window">
    <h3 class="form-head">Insurance for the modern world</h3>
    <base-card>
      <form>
        <div class="form-control">
          <div class="icon">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="M21.75 6.75v10.5a2.25 2.25 0 01-2.25 2.25h-15a2.25 2.25 0 01-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0019.5 4.5h-15a2.25 2.25 0 00-2.25 2.25m19.5 0v.243a2.25 2.25 0 01-1.07 1.916l-7.5 4.615a2.25 2.25 0 01-2.36 0L3.32 8.91a2.25 2.25 0 01-1.07-1.916V6.75" />
            </svg>
          </div>
          <input type="email" id="email" placeholder="E-Mail" v-model="store.email" />
        </div>
        <div class="form-control">
          <div class="icon">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="M16.5 10.5V6.75a4.5 4.5 0 10-9 0v3.75m-.75 11.25h10.5a2.25 2.25 0 002.25-2.25v-6.75a2.25 2.25 0 00-2.25-2.25H6.75a2.25 2.25 0 00-2.25 2.25v6.75a2.25 2.25 0 002.25 2.25z" />
            </svg>
          </div>
          <input type="password" id="password" placeholder="Password" v-model="store.password" />
        </div>
        <div class="buttons-container">
          <base-button class="form-btn" @click="store.register">REGISTER</base-button>
          <div class="logo-btn" @click="store.signInWithGoogle"></div>
        </div>
      </form>
      <div>
        <span class="register-label">Already have an account?</span>
        <base-button @click="login" class="form-btn register-btn" type="button" mode="flat">LOGIN</base-button>
      </div>
    </base-card>
  </div>
</template>

<script setup lang="ts">
  import { useAuthStore } from '../store/auth';
  import { useRouter } from 'vue-router';

  const router = useRouter();
  const store = useAuthStore();

  const login = () => {
    router.push('/');
  }
</script>
  
<style scoped>
  .form-head {
    font-family: 'Caveat', cursive;
    font-style: normal;
    font-weight: 700;
    font-size: 22px;
    line-height: 28px;
    text-align: center;
    letter-spacing: 0.1em;
    text-transform: uppercase;
    color: #000000;
  }
  
  .form-window {
    margin: 0 auto;
    width: 340px;
    height: 400px;
    background: #FFFFFF;
    border: 1px solid #000000;
    border-radius: 20px;
  }
  
  form {
    margin: 1rem;
    padding: 1rem;
  }
  
  .form-control {
    margin: 0.5rem 0;
    display: flex;
    align-items: center;
    gap: 10px;
    border-bottom: 1px solid #E0E0E0;
  }
  
  .icon {
    width: 24px;
    height: 24px;
  }
  
  input,
  textarea {
    display: block;
    width: 100%;
    font: inherit;
    border: none;
    padding: 0.1rem;
  }
  
  input:focus,
  textarea:focus {
    background-color: #E2F4F7;
    outline: none;
  }

  .buttons-container {
    margin-top: 40px;
    display: flex;
    align-items: center;
    gap: 10px;
  }
  .form-btn {
    margin: 0 auto;
    display: flex;
    width: 200px;
    height: 40px;
    background: #33B2FF;
    border: 1px solid transparent;
    border-radius: 20px;
    color: #FFFFFF;
    font-family: 'Inter', sans-serif;
    font-style: normal;
    font-weight: 400;
    font-size: 18px;
    line-height: 22px;
    justify-content: center;
    align-items: center;
    letter-spacing: 0.1em;
    text-transform: uppercase;
    cursor: pointer;
    transition: all ease-in-out 0.3s;
  }
  
  .form-btn:hover {
    background: #FFFFFF;
    color: #33B2FF;
    border-color: #33B2FF;
  }
  
  .logo-btn {
    width: 35px;
    height: 35px;
    background-image: url('../assets/google-logo.png');
    background-position: center;
    background-size: 100%;
    background-repeat: no-repeat;
    cursor: pointer;
    border-radius: 20px;
  }
  
  .register-label {
    width: 283px;
    height: 22px;
    font-family: 'Inter', sans-serif;
    font-weight: 500;
    font-size: 18px;
    line-height: 22px;
    text-align: center;
    letter-spacing: 0.05em;
    text-transform: uppercase;
    color: #000000;
  }
  
  .register-btn {
    margin-top: 0;
    background: #FFFFFF;
    color: #33B2FF;
    border-color: #33B2FF;
  }
  
  .register-btn:hover {
    background: #33B2FF;
    color: #FFFFFF;
    border-color: #FFFFFF;
  }
</style>