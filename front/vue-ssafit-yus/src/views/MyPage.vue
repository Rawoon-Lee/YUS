<template>
  <div>
    <h2>마이 페이지입니다</h2>
    {{ userInfo }}
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
