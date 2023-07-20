<script setup lang="ts">
  import { computed, ComputedRef, onBeforeMount } from 'vue';
  import { useCarStore } from '../store/car';
  import { storeToRefs } from 'pinia';

  import BaseSpinner from '../components/BaseSpinner.vue';
  import NoCarsSection from '../components/NoCarsSection.vue';
  import ObjectIcon from '../components/ObjectIcon.vue';
  import CalendarIcon from '../components/CalendarIcon.vue';
  import DeleteIcon from '../components/DeleteIcon.vue';

  import { Car } from '../shared/interfaces/Car';
  
  const carStore = useCarStore();
  const { getCars, getLoading } = storeToRefs(carStore);

  let cars: ComputedRef<Car[]> = computed((): Car[] => carStore.getCars);
  let loading: ComputedRef<Boolean> = computed((): Boolean => getLoading.value);
  let isCars = computed((): number => getCars.value.length);

  onBeforeMount(async () => {
    await carStore.getAllCarsForOwner();
  });

  const deleteCar = async (car: Car): Promise<void> => {
    await carStore.deleteCar(car);
  }

  // const goRoute = () => {
  //   // if (carStore.getCurrentCar?.contract) {
  //   //   const contract = carStore.getCurrentCar.contract;
  //   //   router.push({ path: '/contract', params: { contract: c } });
  //   // }
  // }
</script>

<template>
  <div class="page-wrapper">
    <div v-if="loading" class="spinner-container">
      <BaseSpinner />
    </div>
    <div v-else>
      <div v-if="isCars > 0" class="with-data">
        <div class="base-header">
          <div @click="$router.push('/newcar')" class="button table-btn">
            <ObjectIcon />
            <span class="btn-span table-span">Add insurance object</span>
          </div>
        </div>
        <div class="page-container">
          <table>
          <tr>
            <th>№</th>
            <th>Name</th>
            <th>VIN</th>
            <th>Status</th>
            <th>Date</th>
            <th>Company</th>
            <th>Details</th>
          </tr>
          <tr v-for="car in cars" :key="car.vin">
            <td>
              <DeleteIcon @click="deleteCar(car)" />
            </td>
            <td class="car-link" @click="() => { carStore.addCarToContract(car); $router.push('/car'); }">{{ car.name }}</td>
            <td class="vin">{{ car.vin }}</td>
            <td v-if="car.contract">
              <div class="status">
                <span class="active-text">Active</span>
              </div>
            </td>
            <td v-else>
              <div class="status inactive">
                <span class="inactive-text">InActive</span>
              </div>
            </td>
            <td>
              <div v-if="car.contract?.startDate" class="vin">
                <CalendarIcon />
              {{ car.contract?.startDate }}
              </div>
              <div v-else>
                No contract
              </div>
            </td>
            <td v-if="car.contract">
              <div class="status company">
                <span>{{ car.contract.insuranceCompany?.name }}</span>
              </div>
            </td>
            <td v-else>
              No company
            </td>
            <td v-if="car.contract">
              <button @click="() => { carStore.addContract(car.contract!); $router.push('/contract'); }" class="button row-btn">See details</button>
            </td>
            <td v-else >
              <button @click="() => { carStore.addCarToContract(car); $router.push('/newcontract'); }" class="button row-btn">Add contract</button>
            </td>
          </tr>
        </table>
        </div>
      </div>
      <NoCarsSection v-else />
    </div>
  </div>
</template>

<style scoped>
.page-wrapper {
  height: calc(100vh - 195px);
}

.base-header {
  padding: 2rem 0;
  margin: 0 auto;
  background-color: #E2F4F7;
  width: 100%;
}

.button {
  margin: 0 auto;
  margin-top: 40px;
  width: 460px;
  height: 104px;
  background: #33B2FF;
  border: 1px solid transparent;
  border-radius: 50px;
  color: #FFFFFF;
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 400;
  font-size: 18px;
  line-height: 22px;
  display: flex;
  align-items: center;
  padding-left: 40px;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  cursor: pointer;
  transition: all ease-in-out 0.3s;
}

.button:hover {
  background: #FFFFFF;
  color: #33B2FF;
  border-color: #33B2FF;
}

.btn-span {
  margin-left: 50px;
}

.table-btn {
  width: 340px;
  height: 65px;
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: space-between;
  letter-spacing: 0.1em;
  padding: 0 20px;
  margin: 0;
  margin-left: auto;
  margin-right: 65px;
}

.table-span {
  margin-left: 0;
}

.row-btn {
  width: 80px;
  height: 26px;
  font-style: normal;
  font-weight: 400;
  font-size: 10px;
  line-height: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  letter-spacing: 0.1em;
  text-transform: capitalize;
  padding: 0;
  margin: 0 auto;
}

table {
   width: 100%;
}
/* границы ячеек первого ряда таблицы */
th {
    font-family: 'Inter', sans-serif;
    font-style: normal;
    font-weight: 500;
    font-size: 12px;
    line-height: 18px;
    align-items: center;
    letter-spacing: 0.03em;
    text-transform: uppercase;
    color: #687182;
    height: 34px;
    background: rgba(247, 249, 252, 0.8);
    backdrop-filter: blur(4px);
}
/* границы ячеек тела таблицы */
td {
  height: 48px;
  border-bottom: 1px solid #E9EDF5;
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 20px;
  color: #171C26;
}

.car-link {
  color: #5E5ADB;
  cursor: pointer;
  transition: all ease-in-out 0.3s;
}

.car-link:hover {
  text-decoration: underline;
  color: #464F60;
}

.status {
  margin: 0 auto;
  width: 66px;
  height: 20px;
  padding: 1px 8px;
  border-radius: 4px;
  background-color: #e3fbef;
  color: #297f4c;
  display: flex;
  align-items: center;
}

.inactive {
  color: #e25766;
  background-color: #fcecee;
}

.active-text::before {
  content: "";
  display: inline-block;
  width: 6px;
  height: 6px;
  border-radius: 2px;
  background-color: #297f4c;
  margin-right: 6px;
}

.inactive-text::before {
  content: "";
  display: inline-block;
  width: 6px;
  height: 6px;
  border-radius: 2px;
  background-color: #e25766;
  margin-right: 6px;
}

.company {
  width: 80px;
  background-color: #e9edf5;
  color: #474f5f;
}

.company span {
  margin: 0 auto;
}

.spinner-container {
  position: absolute;
  top: 50%;
  bottom: 50%;
  left: 0;
  right: 0;
}

.vin {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 3px;
}
</style>