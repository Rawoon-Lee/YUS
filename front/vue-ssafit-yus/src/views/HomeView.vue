<template>
  <div class="home">
    <router-link to="/main">
      <img alt="yus logo" src="../assets/home.png" />
    </router-link>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: "HomeView",
  components: {
    // HelloWorld,
  },
};
</script>
<style scoped>
body {
  background-color: black;
}
</style>
