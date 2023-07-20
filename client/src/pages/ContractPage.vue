<script setup lang="ts">
  import { useRouter } from 'vue-router';
  import { useCarStore } from '../store/car';
  import { storeToRefs } from 'pinia';

  const router = useRouter();
  const carStore = useCarStore();
  const { getCurrentContract } = storeToRefs(carStore);
  const currentContract = getCurrentContract.value;

  const goToConsultant = () => {
    router.push('/consultant');
  };

  const goToInsurance = () => {
    router.push('/insurance');
  };

  const goToPlan = () => {
    router.push('/plan');
  };
</script>

<template>
  <div class="form-window">
    <h2 class="page-head">INSURANCE FOR THE MODERN WORLD</h2>
    <div class="form-container">
      <h3 class="form-head">Details of contract</h3>
      <div class="form-control">
        <div class="form-item">
          <p class="form-paragraph form-span">Company:</p>
          <p class="form-paragraph form-span">Plan:</p>
          <div class="form-spans">
            <span class="form-span">Contract Start date:</span>
            <span class="form-span">Contract End date:</span>
            <span class="form-span">Date of the signing:</span>
          </div>
        </div>
        <div class="form-item">
          <p class="form-paragraph form-value">{{ currentContract?.insuranceCompany?.name }}</p>
          <p class="form-paragraph form-value">
            <button @click="goToPlan" class="form-btn plan-btn" type="button" mode="flat">Plan</button>
          </p>
          <div class="form-spans">
            <span class="form-value">{{ currentContract?.startDate }}</span>
            <span class="form-value">{{ currentContract?.endDate }}</span>
            <span class="form-value">{{ currentContract?.startDate }}</span>
          </div>
        </div>
      </div>
      <div class="form-control">
        <span class="form-span">Consultant</span>
        <button @click="goToConsultant" class="form-btn link-btn" type="button" mode="flat">SEE DATA</button>
      </div>
      <button @click="goToInsurance" class="form-btn" type="button" mode="flat">BACK</button>
    </div>
  </div>
</template>
  
<style scoped>
p {
  margin: 0;
}
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
    font-weight: 400;
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
    position: relative;
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

  .form-paragraph {
    text-align: start;
    margin-bottom: 1rem;
  }

  .form-spans {
    margin-top: 2rem;
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

.link-btn {
  margin-left: 120px;
  margin-top: 0;
  width: 150px;
  height: 30px;
  background: #33B2FF;
  border: 1px solid transparent;
  color: #FFFFFF;
}

.link-btn:hover {
  background: #FFFFFF;
  color: #33B2FF;
  border-color: #33B2FF;
}

.plan-btn {
  margin-top: 0;
  width: 120px;
  height: 25px;
}
  
</style>