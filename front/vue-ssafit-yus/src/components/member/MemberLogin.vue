<template>
  <div class="container">
    <h1>식단 등록</h1>
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
            placeholder="제목을 입력해주세요"
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
            trim
            placeholder="제목을 입력해주세요"
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
            id="input-2"
            v-model="imgFilePath"
            :state="Boolean(imgFilePath)"
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
          <b-form-input id="weight" v-model="weight"></b-form-input>
        </b-form-group>
        <b-form-group
          label="키"
          label-for="height"
          label-cols-xl="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input id="height" v-model="height"></b-form-input>
        </b-form-group>
        <b-form-group
          label="목적"
          label-for="purpose"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input list="input-list" v-model="purpose_text"></b-form-input>
          <datalist id="input-list">
            <option
              v-for="(pp, index) in purpose"
              :key="index"
              :value="pp.value"
            >
              {{ pp.text }}
            </option>
          </datalist>
        </b-form-group>
        <b-button variant="outline-success" @click="insertUser">등록</b-button>
      </div>
    </b-card>

    {{ purpose_text }}
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: null,
      pw: null,
      imgFilePath: null,
      weight: null,
      height: null,
      purpose: [
        { text: "체중 감량", value: 0 },
        { text: "체중 유지", value: 1 },
        { text: "체중 증량", value: 2 },
      ],
      purpose_text: null,
    };
  },
  methods: {
    insertUser() {
      let newUser = {
        id: this.id,
        pw: this.pw,
        imgFilePath: this.imgFilePath,
        weight: this.weight,
        height: this.height,
      };
      this.$store.dispatch("createMeal", newUser);
    },
  },
};
</script>

<style></style>
