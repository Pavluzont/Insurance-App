<script lang="ts">
import ObjectIcon from './ObjectIcon.vue';
  export default {
    components: {
      ObjectIcon
    }
  };
</script>

<template>
  <div class="no-data">
    <div class="base-header">
      <h2 class="page-head">INSURANCE FOR THE MODERN WORLD</h2>
      <span class="page-span">You don't have any insurance policies yet</span>
    </div>
    <div @click="$router.push('/newcar')" class="button">
      <ObjectIcon />
      <span class="btn-span">Add insurance object</span>
    </div>
  </div>
</template>
    
<style scoped>
.base-header {
  padding: 2rem 0;
  margin: 0 auto;
  background-color: #E2F4F7;
  width: 100%;
}

.page-head {
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

.page-span {
  font-family: 'Inter', sans-serif;
  font-style: normal;
  font-weight: 700;
  font-size: 36px;
  line-height: 44px;
  text-align: center;
  color: #000000;
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
</style>
