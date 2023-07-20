<template>
  <nav>
    <ul>
      <li>
        <router-link class="nav-link" to="/profile">Profile</router-link>
      </li>
      <li>
        <router-link class="nav-link" to="/insurance">My Insurance</router-link>
      </li>
    </ul>
  </nav>
</template>

<script lang="ts">
  export default {
  };
</script>

<style scoped>
  ul {
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  li {
    margin: 0 0.5rem;
  }

  .nav-link {
    text-decoration: none;
    font-size: 20px;
    color: #000000;
    display: flex;
    transition: all ease-in-out 0.3s;
  }

  .nav-link:hover {
    color: #33B2FF;
  }

  .router-link-active{
    color: #33B2FF;
  }
</style>
