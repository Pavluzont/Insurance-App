<script lang="ts">
import PhoneIcon from './PhoneIcon.vue';
import MailIcon from './MailIcon.vue';

export default {
  components: {
    PhoneIcon,
    MailIcon
  }
};
</script>

<template>
  <footer class="footer-wrapper">
    <div class="footer-container">
      <div class="footer-contacts">
        <h4 class="contacts-title">
          Contact tech support
        </h4>
        <a class="footer-container__link" href="tel:+34688834888" target="_blank">
          <PhoneIcon />
          <span>+34 688 834 888</span>
        </a>
        <a class="footer-container__link" href="mailto:ptsitou@exadel.com" target="_blank">
          <MailIcon />
          <span>ptsitou@exadel.com</span>
        </a>
      </div>
      <span>©2023</span>
      <a class="footer-container__link" href="https://github.com/PavelTitov16" target="_blank">
        <h4>Created by Pavel Tsitou</h4>
      </a>
    </div>
  </footer>
</template>

<style scoped>
  .footer-wrapper {
    width: 100%;
    background-color: #E2F4F7;
    border-top: 1px solid #000000
  }

  .footer-container {
    margin: 0 auto;
    max-width: 1440px;
    height: 90px;
    padding: 0 65px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .footer-contacts {
    display: flex;
    flex-direction: column;
    gap: 8px;
    text-align: start;
  }

  .contacts-title {
    font-family: 'Inter', sans-serif;
    font-style: normal;
    font-weight: 900;
    font-size: 20px;
    line-height: 18px;
    color: #000000;
    margin: 0;
  }

  .footer-container span {
    font-size: 18px;
    font-style: bold;
    color: #000000;
  }

  .footer-container__link {
    font-family: 'Inter', sans-serif;
    font-style: normal;
    font-weight: 400;
    font-size: 20px;
    line-height: 9px;
    display: flex;
    align-items: center;
    gap: 4px;
    text-align: left;
    letter-spacing: 0.1em;
    text-transform: lowercase;
    color: #000000;
    transition: all ease-in-out 0.3s;
  }

  .footer-container__link:hover {
    color: #33B2FF;
  }

  .footer-container__link span:hover {
    color: #33B2FF;
    transition: all ease-in-out 0.3s;
  }

  .icon {
    width: 18px;
  }

  .footer-container a {
    text-transform: none;
  }
</style>
