<script setup lang="ts">
  import { useRouter } from 'vue-router';
  import { useCarStore } from '../store/car';
  import { storeToRefs } from 'pinia';

  const carStore = useCarStore();
  const { getCurrentContract } = storeToRefs(carStore);
  const currentContractPlan = getCurrentContract.value?.plan;

  const router = useRouter();
  const goBack = () => {
    router.push('/contract');
  }
</script>

<template>
  <div class="form-window">
    <h2 class="page-head">INSURANCE FOR THE MODERN WORLD</h2>
    <div class="form-container">
      <h3 class="form-head">{{ currentContractPlan?.name }}</h3>
      <h4 class="form-subhead">Conditions</h4>
        <div class="form-control">
          <div class="form-item">
            <span class="form-span">PRICE:</span>
            <span class="form-span">DURATION:</span>
          </div>
          <div class="form-item">
            <span class="form-value">{{ `${currentContractPlan?.price} ${"$"}` }}</span>
            <span class="form-value">{{ `${currentContractPlan?.duration} ${"months"}` }}</span>
          </div>
        </div>
        <div>
          <span class="form-span">DESCRIPTION:</span>
          <span class="form-value">{{ currentContractPlan?.description }}</span>
        </div>
        <button @click="goBack" class="form-btn" type="button" mode="flat">BACK</button>
    </div>
  </div>
</template>

<style scoped>
.form-window {
  width: 700px;
  background: #E2F4F7;
  border-radius: 20px;
  margin: 0 auto;
  padding: 1rem;
  box-sizing: border-box;
}

.page-head {
  font-family: 'Caveat', cursive;
  font-style: normal;
  font-weight: 700;
  font-size: 22px;
  line-height: 28px;
  text-align: end;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: #000000;
}

.form-head {
  font-family: 'Inter', sans-serif;
  font-style: italic;
  font-weight: 600;
  font-size: 24px;
  line-height: 29px;
  display: flex;
  align-items: center;
  text-align: center;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: #000000;
}

.form-subhead {
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 600;
  font-size: 18px;
  line-height: 22px;
  display: flex;
  align-items: center;
  text-align: center;
  letter-spacing: 0.1em;
  color: #000000;
}

.form-container {
  margin-top: 1rem;
  padding: 0 50px;
}

.form-control {
  margin: 0.4rem 0;
  display: flex;
  align-items: center;
  gap: 30px;
}

.form-item {
  display: flex;
  flex-direction: column;
}

.form-span {
  font-family: 'Inter';
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 24px;
  display: flex;
  align-items: center;
  text-align: center;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: #000000;
}

.form-value {
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 20px;
  line-height: 24px;
  display: flex;
  align-items: center;
  text-align: left;
  color: #000000;
}

.form-btn {
    display: flex;
    width: 150px;
    height: 40px;
    border: 1px solid #33B2FF;
    border-radius: 20px;
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
    background: #FFFFFF;
    color: #33B2FF;
    margin-top: 70px;
    align-self: flex-end;
    margin-left: auto;
  }
  
  .form-btn:hover {
    background: #33B2FF;
    color: #FFFFFF;
    border-color: #FFFFFF;
  }

</style>