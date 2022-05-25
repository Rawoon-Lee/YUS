<template>
  <header>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand :to="{ name: 'main' }">
        <img src="@/assets/logo.png" alt="" width="100px" height="" />
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml" align="left">
          <b-nav-item to="/routine">루틴</b-nav-item>
          <b-nav-item to="/challenge">운동 챌린지</b-nav-item>
          <b-nav-item to="/meal">식단 게시판</b-nav-item>
          <b-nav-item to="/exercise/all">운동정보</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" align="right"> </b-navbar-nav>
        <b-navbar-nav v-show="!isLogin" class="ml-auto" align="right">
          <b-nav-item :to="{ name: 'login' }">로그인</b-nav-item>
          <b-nav-item to="/member/join">회원가입</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav v-show="isLogin" class="ml-auto" align="right">
          <b-nav-item to="/mypage">
            <img :src="path" alt="프로필" width="40px" height=""
          /></b-nav-item>
          <b-nav-item @click="logout">로그아웃</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </header>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "HeaderNav",
  data() {
    return {
      userId: null,
      path: require("/src/assets/profile-default.png"),
    };
  },
  computed: {
    ...mapState(["isLogin"]),
    ...mapState(["userInfo"]),
  },
  created() {
    this.$store.dispatch("userIsLogin");
    this.userId = sessionStorage.getItem("USER_ID");
    const profile = "/src/assets/UserInfo/" + this.userId + ".png";
    console.log(this.userId);
    if (this.userInfo["filepath"]) {
      this.$store.dispatch("getUserInfo", this.userId);
      this.path = require(profile);
      console.log(this.path);
    }
  },
  methods: {
    logout() {
      sessionStorage.clear();
      this.$store.dispatch("logout");
    },
  },
};
</script>

<style></style>
