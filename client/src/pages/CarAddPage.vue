<script setup lang="ts">
  import { useRouter } from 'vue-router';
  import { useCarStore } from '../store/car';
  import { useOwnerStore } from '../store/owner';

  import { ref } from 'vue';

  const carStore = useCarStore();
  const ownerStore = useOwnerStore();
  const name = ref("");
  const model = ref("");
  const vin = ref("");
  const color = ref("");
  const plate = ref("");

  const router = useRouter();

  console.log(ownerStore.owner);

  const onSubmit = async () => {
    await carStore.createCar(
      {
        name: name.value,
        model: model.value,
        vin: vin.value,
        color: color.value,
        plateNum: plate.value,
      }
    );
    
    await router.push('/insurance');
  }
</script>

<template>
  <div class="form-window">
    <h2 class="page-head">INSURANCE FOR THE MODERN WORLD</h2>
    <div class="form-container">
      <h3 class="form-head">Add new car</h3>
      <form>
        <div class="form-control">
          <label for="name">Name: </label>
          <input type="text" id="name" v-model="name" />
        </div>
        <div class="form-control">
          <label for="model">Model: </label>
          <input type="text" id="model" v-model="model" />
        </div>
        <div class="form-control">
          <label for="vin">VIN: </label>
          <input type="text" id="vin" v-model="vin" />
        </div>
        <div class="form-control">
          <label for="color">Color: </label>
          <input type="text" id="color" v-model="color" />
        </div>
        <div class="form-control">
          <label for="plate">Plate №: </label>
          <input type="text" id="plate" v-model="plate" />
        </div><div class="btn-container">
          <base-button @click="$router.push('/insurance')" class="form-btn cancel-btn">Cancel</base-button>
          <base-button @click="onSubmit" class="form-btn" type="button" mode="flat">SAVE</base-button>
        </div>
      </form>
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
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 20px;
  text-align: start;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: #000000;
}

.form-container {
  margin-top: 1rem;
  padding: 0 50px;
}

.form-control {
  margin: 0.5rem 0;
}

label {
  font-weight: bold;
  display: block;
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 20px;
  display: flex;
  align-items: center;
  text-align: center;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: #000000;
}

input {
  display: block;
  width: 50%;
  font: inherit;
  border: none;
}

input:focus,
textarea:focus {
  outline: none;
}

.form-control {
  margin: 0.4rem 0;
  width: 400px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}



.btn-container {
  margin-top: 1rem;
  display: flex;
  justify-content: end;
  gap: 40px;
}

.form-btn {
    display: flex;
    width: 150px;
    height: 40px;
    border: 1px solid #FFFFFF;
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
    background: #33B2FF;
    color: #FFFFFF;
    margin-top: 70px;
    align-self: flex-end;
    margin-left: auto;
}
  
.form-btn:hover {
    background: #FFFFFF;
    color: #33B2FF;
    border-color: #33B2FF;
}

.cancel-btn {
  margin-top: 0;
  background: #FFFFFF;
  color: #33B2FF;
  border-color: #33B2FF;
}

.cancel-btn:hover {
  background: #33B2FF;
  color: #FFFFFF;
  border-color: #FFFFFF;
}

</style>
