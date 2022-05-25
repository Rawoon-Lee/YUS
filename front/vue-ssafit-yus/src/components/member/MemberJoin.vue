<template>
  <div class="container">
    <h1>회원가입</h1>
    <b-alert v-model="showDuplicatedError" variant="danger" dismissible>
      이미 존재하는 아이디입니다. 다른 아이디로 시도해주세요.
    </b-alert>
    <b-alert v-model="showInternalError" variant="danger" dismissible>
      유효하지 않은 요청입니다. 올바르게 작성했는지 확인해주세요.
    </b-alert>
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
              trim
              placeholder="제목을 입력해주세요"
              type="password"
            ></b-form-input>
          </b-form-group>
          <!-- <b-form-group
          label="비밀번호 확인"
          label-for="input-2"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input
            id="input-2"
            v-model="pw_check"
            trim
            placeholder="제목을 입력해주세요"
            type="password"
          ></b-form-input>
        </b-form-group> -->
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
              @change="profileInput()"
            ></b-form-file>
          </b-form-group>
          <b-form-group
            label="나이"
            label-for="age"
            label-cols-md="2"
            label-align="left"
            label-size="lg"
          >
            <b-form-input id="age" v-model="age" type="number"></b-form-input>
          </b-form-group>
          <b-form-group
            label="성별"
            label-cols-md="2"
            label-align="left"
            label-size="lg"
          >
            <b-form-select v-model="gender" :options="genders">
              <template #first>
                <b-form-select-option :value="null" disabled
                  >성별을 골라주세요</b-form-select-option
                >
              </template>
            </b-form-select>
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
                <b-form-select-option :value="null" disabled
                  >운동 목적을 골라주세요</b-form-select-option
                >
              </template>
            </b-form-select>
          </b-form-group>
          <b-button variant="outline-success" @click="insertUser"
            >등록</b-button
          >
        </b-form>
      </div>
    </b-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: null,
      pw: null,
      // pw_check: null,
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
          text: "여성",
          value: 1,
        },
        {
          text: "남성",
          value: 0,
        },
      ],
    };
  },
  methods: {
    profileInput() {
      console.log(this.imgFilePath);
    },
    insertUser() {
      let formData = new FormData();
      formData.append("userId", this.id);
      formData.append("userPassword", this.pw);
      formData.append("file", this.imgFilePath);
      formData.append("weight", this.weight);
      formData.append("height", this.height);
      formData.append("gender", this.gender);
      formData.append("age", this.age);
      formData.append("purpose", this.purpose_text);
      console.log(formData);
      this.$store.dispatch("createUser", formData);
    },
  },
};
</script>

<style></style>
