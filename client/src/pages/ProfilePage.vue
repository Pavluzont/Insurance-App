<script setup lang="ts">
  import { useOwnerStore } from '../store/owner';

  import { Gender } from '../shared/enums/Gender';
  import { ref } from 'vue';
  import { storeToRefs } from 'pinia';
  import { useRouter } from 'vue-router';

  const ownerStore = useOwnerStore();
  const owner = storeToRefs(ownerStore);
  const firstName = ref(owner.getFirstName.value);
  const lastName = ref(owner.getLastName.value);
  const age = ref(owner.getAge.value);

  const restrictInputToNumbers = (event: Event): void => {
    const inputElement = event.target as HTMLInputElement;
    age.value = Number(inputElement.value.replace(/\D/g, ''));
  };
  
  const id = ref(owner.getId.value);
  const gender = ref(owner.getGender.value);
  const phoneNumber = ref(owner.getPhoneNumber.value);
  const email = ref(owner.getEmail.value);
  const country = ref(owner.getAdress.value?.country);
  const state = ref(owner.getAdress.value?.state);
  const city = ref(owner.getAdress.value?.city);
  const street = ref(owner.getAdress.value?.street);
  const homeNum = ref(owner.getAdress.value?.homeNum);
  const postCode = ref(owner.getAdress.value?.postCode);

  const router = useRouter();

  const female = Gender.FEMALE;
  const male = Gender.MALE;

  const updateOwnerOnSubmit = async () => {
    router.push('/insurance');

    ownerStore.updateOwner(
      {
        id: id.value,
        firstName: firstName.value,
        lastName: lastName.value,
        age: age.value,
        gender: gender.value,
        phoneNumber: phoneNumber.value,
        email: email.value,
        address: {
          country: country.value,
          state: state.value,
          city: city.value,
          street: street.value,
          homeNum: homeNum.value,
          postCode: postCode.value
        }
      }
    );
  }
</script>

<template>
  <div class="form-window">
    <h3 class="form-head">Personal information</h3>
    <div class="form-container">
      <form>
        <div class="form-control">
          <label for="firstName">First Name: </label>
          <input type="text" id="firstName" v-model="firstName" required />
        </div>
        <div class="form-control">
          <label for="lastName">Last Name: </label>
          <input type="text" id="lastName" v-model="lastName" required />
        </div>
        <div class="form-control">
          <label for="age">Age: </label>
          <input type="text" id="age" v-model="age" @input="restrictInputToNumbers" required />
        </div>
        <div class="form-control">
          <label>Gender: </label>
          <div class="gender-item">
            <div>
              <input
                type="radio"
                id="female"
                :value=female
                v-model="gender"
                :checked="gender === female"
              />
              <label for="female">{{ female.valueOf() }}</label>
            </div>
            <div>
              <input
                type="radio"
                id="male"
                :value="male"
                v-model="gender"
                :checked="gender === male"
              />
              <label for="male">{{ male.valueOf() }}</label>
            </div>
          </div>
        </div>
        <div class="form-control">
          <label for="phoneNumber">Phone: </label>
          <input type="tel" id="phoneNumber" v-model.trim="phoneNumber" />
        </div>
        <div class="form-control">
          <label for="email">Email: </label>
          <input type="email" id="email" v-model="email" disabled />
        </div>
        <p class="adress-head">Address:</p>
        <div class="form-control">
          <label for="country">Country: </label>
          <input type="text" id="country" v-model="country" />
        </div>
        <div class="form-control">
          <label for="state">State: </label>
          <input type="text" id="state" v-model="state" />
        </div>
        <div class="form-control">
          <label for="city">City: </label>
          <input type="text" id="city" v-model="city" />
        </div>
        <div class="form-control">
          <label for="street">Street: </label>
          <input type="text" id="street" v-model="street" />
        </div>
        <div class="form-control">
          <label for="homenum">Home: </label>
          <input type="text" id="homenum" v-model="homeNum" />
        </div>
        <div class="form-control">
          <label for="postcode">Postcode: </label>
          <input type="text" id="postcode" v-model="postCode" />
        </div>
        <base-button @click="updateOwnerOnSubmit" class="save-btn">Save</base-button>
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

.form-head {
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 20px;
  text-align: end;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: #000000;
  margin: 0;
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

input[type='checkbox'] + label {
  font-weight: normal;
  display: inline;
  margin: 0 0 0 0.5rem;
}

input[type='checkbox'] {
  display: inline;
  width: auto;
  border: none;
}

input[type='checkbox']:focus {
  outline: #3d008d solid 1px;
}

.invalid label {
  color: red;
}

.invalid input,
.invalid textarea {
  border: 1px solid red;
}

.form-control {
  margin: 0.4rem 0;
  width: 400px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.gender-item {
  display: flex;
  gap: 20px;
}

.adress-head {
  text-align: start;
  font-weight: bold;
  display: block;
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 700;
  font-size: 16px;
  line-height: 20px;
  display: flex;
  align-items: center;
  text-align: center;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: #000000;
  padding-left: 2rem;
  margin: 0.1rem;
}

.btn-container {
  display: flex;
  justify-content: end;
  gap: 20px;
}

.save-btn {
  display: flex;
  width: 120px;
  height: 30px;
  border: 1px solid #33B2FF;
  border-radius: 20px;
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
  margin-left: auto;
  background: #FFFFFF;
  color: #33B2FF;
}

.save-btn:hover {
  background: #33B2FF;
  color: #FFFFFF;
  border-color: #FFFFFF;
}
</style>