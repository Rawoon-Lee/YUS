<template>
  <div>
    <h2>마이페이지</h2>
    <b-button :to="{ name: 'myInfoUpdate' }">개인정보 수정하기</b-button>
    <img
      :src="require(`@/assets/UserInfo/${userId}.png`)"
      alt="프로필"
      class="mypageProfile"
    />
    <img
      :src="require(`@/assets/UserInfo/${userId}.png`)"
      alt="프로필"
      class="mypageProfile"
    />
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      userId: sessionStorage.getItem("USER_ID"),
    };
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  created() {
    this.userId = sessionStorage.getItem("USER_ID");
    console.log(this.userId);
    this.$store.dispatch("getUserInfo", this.userId);
  },
};
</script>

<style>
.mypageProfile {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  object-fit: cover;
}
</style>
