<script setup lang="ts">
  import { useCarStore } from '../store/car';
  import { storeToRefs } from 'pinia';

  const carStore = useCarStore();
  const { getCurrentCompany } = storeToRefs(carStore);
  const currentCompany = getCurrentCompany.value;
</script>

<template>
  <div class="page-wrapper">
    <div class="with-data">
      <div class="base-header">
        <h2>{{ currentCompany?.name }}</h2>
      </div>
      <div class="page-container">
        <p class="page-text">
          Simple and quick design. 
          24/7 support.
          Our insurance assistant will support you in any case.
          We will refund the money in case of unforeseen circumstances and cancellation of the trip.
          Protection of personal data.
          It is very simple and fast to issue insurance with us.
        </p>
        <h3 class="page-head">Our Plans:</h3>
        <div class="plans-container">
          <div v-for="plan in currentCompany?.plans" :key="plan.name" class="plans-item">
            <h4 class="plans-item__header">{{ plan.name }}</h4>
            <h4 class="plans-item__price">{{ `${plan.price} ${'$'}` }}</h4>
            <h4 class="plans-item__duration">{{ `${plan.duration} ${'months'}` }}</h4>
            <p class="plans-item__description">{{ plan.description }}
            </p>
            <p class="plans-item__conditions">
              <span>For all cars</span>
              <span>For foreigners and EU citizens</span>
            </p>
          </div>
        </div>
        <base-button @click="$router.push('/newcontract')" class="back-btn">Go back</base-button>
      </div>
    </div>
  </div>
</template>

<style scoped>
h4, p {
  margin: 0;
}

.with-data {
  position: relative;
}
.page-wrapper {
  height: calc(100vh - 195px);
}

.base-header {
  padding: 1rem 0;
  margin: 0 auto;
  background-color: #E2F4F7;
  width: 100%;
}

.page-container {
  max-width: 1440px;
  padding: 20px 65px;
}

.page-text {
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 24px;
  text-align: center;
  color: #000000;
}

.page-head {
  font-family: 'Inter', sans-serif;
  font-size: 20px;
  line-height: 24px;
}

.plans-container {
  padding: 1rem 8rem;
  display: flex;
  justify-content: space-between;
}

.plans-item {
  max-width: 40%;
  display: flex;
  flex-direction: column;
  gap: 0.9rem;
}

.plans-item__conditions {
  display: flex;
  flex-direction: column;
}

.back-btn {
  position: absolute;
  top: 1.5rem;
  right: 5rem;
  display: flex;
  width: 120px;
  height: 30px;
  background: #33B2FF;
  border: 1px solid transparent;
  border-radius: 20px;
  color: #FFFFFF;
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 18px;
  justify-content: center;
  align-items: center;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  cursor: pointer;
  transition: all ease-in-out 0.3s;
}

.back-btn:hover {
  background: #FFFFFF;
  color: #33B2FF;
  border-color: #33B2FF;
}
</style>