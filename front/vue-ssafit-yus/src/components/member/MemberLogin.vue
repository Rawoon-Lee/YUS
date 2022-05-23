<template>
  <div class="container">
    <h2>로그인 페이지</h2>
    <b-card bg-variant="light">
      <div class="m-4">
        <b-form-group
          label="아이디"
          label-for="input-1"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input
            id="input-1"
            v-model="id"
            trim
            placeholder="아이디를 입력해주세요"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          label="비밀번호"
          label-for="input-2"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input
            id="input-2"
            v-model="pw"
            type="password"
            trim
            placeholder="비밀번호를 입력해주세요"
          ></b-form-input>
        </b-form-group>
        <b-button variant="outline-success" @click="loginUser">로그인</b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "MemberLogin",
  data() {
    return {
      id: null,
      pw: null,
    };
  },
  methods: {
    loginUser() {
      let user = {
        userId: this.id,
        userPassword: this.pw,
      };
      let newUser = JSON.stringify(user);
      this.$store.dispatch("userLogin", newUser);
    },
  },
};
</script>

<style></style>
