<script setup lang="ts">
  import { reactive, onMounted, ref } from 'vue';
  import { useCarStore } from '../store/car';
  import { storeToRefs } from 'pinia';

  import { CompanyService } from '../services/CompanyService';
  import { Company } from '../shared/interfaces/Company';
  import { ContractService } from '../services/ContractService';
  import { Consultant } from '../shared/interfaces/Consultant';
  import { Plan } from '../shared/interfaces/Plan';

  import BaseSpinner from '../components/BaseSpinner.vue';

  const carStore = useCarStore();
  const { getCurrentCar } = storeToRefs(carStore);
  const currentCar = getCurrentCar.value;

  let companies = ref<Company[]>([]);

  const carLabel = `${currentCar?.name} ${currentCar?.model}`;
  const startDate = ref(new Date());

  interface Data {
    companies: Company[] | null;
    plans: Plan[] | null,
    consultants: Consultant[] | null,
  }

  const data: Data = reactive({
    companies: null,
    plans: null,
    consultants: null,
  });

  const selectedCompany = ref<Company | null>(null);
  const selectedPlan = ref<Plan | null>(null);
  const selectedConsultant = ref<Consultant | null>(null);

  onMounted(async () => {
  if (!data.companies) {
      await CompanyService.getAllCompanies()
      .then((responseData) => {
        data.companies = responseData;
      })
    }
  });

  const updateRef = () => {
      selectedCompany.value = selectedCompany.value;

      if (selectedCompany.value) {
        carStore.addCompany(selectedCompany.value);
        selectedPlan.value = null;
        selectedConsultant.value = null;
      }
    };

  const onSubmitContract = () => {
    let endDate: Date = new Date(startDate.value);
    let duration = Number(selectedPlan.value?.duration);
    endDate.setMonth(endDate.getMonth() + duration);

    const formattedEndDate = endDate.toISOString().split('T')[0];

    const contract = {
      plan: selectedPlan.value ?? undefined,
      insuranceCompany: selectedCompany.value ?? undefined,
      consultant: selectedConsultant.value ?? undefined,
      startDate: startDate.value,
      endDate: formattedEndDate
    }

    if (currentCar) {
      ContractService.addContract(contract)
      .then((data) => {
        currentCar.contract = data;
        carStore.updateCar(currentCar);
        console.log(currentCar);
      })
    }
  }

  // const redirectToCompanyPage = () => {
  //   // const route = useRouter();
  //   // console.log(route);
  //   if (selectedCompany) {
  //     useRouter().push({
  //       name: 'companyPage',
  //       query: { company: selectedCompany.value }, 
  //     });
  //   }
  // }
</script>

<template>
  <div class="form-window">
    <h2 class="page-head">INSURANCE FOR THE MODERN WORLD</h2>
    <div class="form-container" v-if="companies">
      <h3 class="form-head">Add a contract</h3>
      <form>
        <div class="form-control">
          <label for="car">Car: </label>
          <input v-if="currentCar" type="text" id="car" v-model="carLabel" disabled />
        </div>
        <div class="form-control">
          <label for="vin">VIN: </label>
          <input v-if="currentCar" type="text" id="vin" v-model="currentCar.vin" disabled />
        </div>
        <div class="form-control">
          <label for="plate">Plate: </label>
          <input v-if="currentCar" type="text" id="plate" v-model="currentCar.plateNum" disabled />
        </div>

        <div class="form-control">
          <label for="company">Company: </label>
          <select class="input" id="company" v-model="selectedCompany" @change="updateRef" required>
            <option v-for="company in data.companies" :key="company.id" :value="company">{{ company.name }}</option>
          </select>
        </div>

        <div class="form-control">
          <label for="plan">Plan: </label>
          <select class="input" id="plan" v-model="selectedPlan" v-if="selectedCompany" required>
            <option v-for="plan in selectedCompany.plans" :key="plan.id" :value="plan">{{ plan.name }}</option>
          </select>
          <select class="input" id="plan" v-model="selectedPlan" v-else>
            <option>Choose company before</option>
          </select>
        </div>

        <div class="form-control">
          <label for="consultant">Consultant: </label>
          <select class="input" id="consultant" v-model="selectedConsultant"  v-if="selectedCompany" required>
            <option v-for="consultant in selectedCompany.consultants" :key="consultant.id" :value="consultant">{{ `${consultant.firstName} ${consultant.lastName}` }}</option>
          </select>
          <select class="input" id="plan" v-model="selectedPlan" v-else>
            <option>Choose company before</option>
          </select>
        </div>
        <div class="form-control">
          <label for="date">Start date: </label>
          <input type="date" id="date" v-model="startDate" pattern="\d{2}-\d{2}-\d{4}" required />
        </div>
        <div class="btn-container">
          <base-button @click="$router.push('/insurance')" class="change-btn save-btn">Cancel</base-button>
          <base-button @click="() => { onSubmitContract(); $router.push('/insurance'); }" class="change-btn">Save</base-button>
        </div>
      </form>
      <base-button v-if="selectedCompany" @click="$router.push('/company')" class="change-btn route-btn">See details</base-button>
    </div>
    <div v-else>
      <BaseSpinner />
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
  position: relative;
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

input, select {
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

.change-btn {
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

.change-btn:hover {
  background: #FFFFFF;
  color: #33B2FF;
  border-color: #33B2FF;
}

.save-btn {
  margin-top: 0;
  background: #FFFFFF;
  color: #33B2FF;
  border-color: #33B2FF;
}

.save-btn:hover {
  background: #33B2FF;
  color: #FFFFFF;
  border-color: #FFFFFF;
}

.route-btn {
  position: absolute;
  top: 8rem;
  right: 5rem;
}
</style>