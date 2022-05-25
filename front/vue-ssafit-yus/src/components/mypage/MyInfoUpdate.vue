<template>
  <div class="container">
    <h1>회원가입</h1>
    <b-card bg-variant="light">
      <div class="m-4">
        <b-form @submit.prevent>
          <b-form-group
            label="아이디"
            label-for="input-1"
            label-cols-md="2"
            label-align="left"
            label-size="lg"
          >
            <b-form-input
              id="input-1"
              trim
              :value="userInfo.userId"
              disabled
            ></b-form-input>
          </b-form-group>
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
          <b-form-group
            label="몸무게"
            label-for="weight"
            label-cols-md="2"
            label-align="left"
            label-size="lg"
          >
            <b-form-input
              id="weight"
              v-model="weight"
              type="number"
              :placeholder="String(userInfo.weight)"
            ></b-form-input>
          </b-form-group>
          <b-form-group
            label="키"
            label-for="height"
            label-cols-md="2"
            label-align="left"
            label-size="lg"
          >
            <b-form-input
              id="height"
              v-model="height"
              type="number"
              :placeholder="String(userInfo.height)"
            ></b-form-input>
          </b-form-group>
          <b-form-group
            label="목적"
            label-for="purpose"
            label-cols-md="2"
            label-align="left"
            label-size="lg"
          >
            <b-form-select v-model="purpose_text" :options="purpose">
              <template #first>
                <b-form-select-option :value="null" disabled>{{
                  purpose[userInfo.purpose].text
                }}</b-form-select-option>
              </template>
            </b-form-select>
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
      formData.append("age", this.userInfo.age);
      formData.append(
        "weight",
        this.weight ? this.weight : this.userInfo.weight
      );
      formData.append(
        "height",
        this.height ? this.weight : this.userInfo.height
      );
      formData.append(
        "purpose",
        this.purpose_text ? this.weight : this.userInfo.weight
      );
      formData.append("gymName", this.userInfo.gymName);
      console.log(formData);
      this.$store.dispatch("updateUserInfo", formData);
    },
  },
};
</script>

<style></style>
