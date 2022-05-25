<template>
  <div>
    <div style="height:200px; background-color:rgba(0,0,0,0.2); display:flex; margin:auto">
      <h1 style="text-align:center; font-size:60pt; font-style:italic; margin:auto; font-weight:bold">MY PAGE</h1>
    </div>
    <div style="height:65%">
      {{ userInfo }}  
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState(["userInfo"]),
  },
  created() {
    let userId = sessionStorage.getItem("USER_ID");
    this.$store.dispatch("getUserInfo", userId);
  },
};
</script>

<style></style>
