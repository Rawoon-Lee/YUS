<template>
  <div class="container">
    <h1>프로필 사진 수정</h1>
    <b-card bg-variant="light">
      <div class="m-4">
        <b-form @submit.prevent>
          <b-form-group
            label="프로필"
            label-for="input-3"
            label-cols-md="2"
            label-align="left"
            label-size="lg"
          >
            <b-form-file
              accept=".jpg, .png"
              id="input-3"
              v-model="imgFilePath"
              placeholder="프로필 사진을 첨부해주세요"
            ></b-form-file>
          </b-form-group>
          <b-button variant="outline-success" @click="updateUser"
            >등록</b-button
          >
        </b-form>
      </div>
    </b-card>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      imgFilePath: null,
      age: null,
      gender: null,
      weight: null,
      height: null,
      purpose: [
        { text: "체중 감량", value: 0 },
        { text: "체중 유지", value: 1 },
        { text: "체중 증량", value: 2 },
      ],
      purpose_text: null,
      showDuplicatedError: false,
      showInternalError: false,
      genders: [
        {
          text: "남성",
          value: 0,
        },
        {
          text: "여성",
          value: 1,
        },
      ],
    };
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  created() {
    let userId = sessionStorage.getItem("USER_ID");
    this.$store.dispatch("getUserInfo", userId);
  },
  methods: {
    updateUser() {
      let userId = sessionStorage.getItem("USER_ID");
      let formData = new FormData();
      formData.append("userId", userId);
      formData.append("file", this.imgFilePath);
      formData.append("filepath", this.userInfo.filepath);
      console.log(formData);
      console.log("프로필 업데이트 시작");
      this.$store.dispatch("updateUserInfoProfile", formData);
    },
  },
};
</script>

<style></style>
