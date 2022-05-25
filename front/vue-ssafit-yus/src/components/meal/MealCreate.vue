<template>
  <div class="container">
  <br>
    <h1 style="text-align:center">식단 등록</h1>
    <hr>
    <b-card bg-variant="light">
      <div class="m-4">
        <b-form-group
          label="제목"
          label-for="input-1"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input
            id="input-1"
            v-model="title"
            trim
            placeholder="제목을 입력해주세요"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          label="사진"
          label-for="input-2"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-file
            accept=".jpg, .png"
            id="input-2"
            v-model="imgFilePath"
            :state="Boolean(imgFilePath)"
            placeholder="식단 사진을 첨부해주세요"
          ></b-form-file>
        </b-form-group>
        <b-form-group
          label="내용"
          label-for="textarea"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-textarea
            id="textarea"
            v-model="content"
            placeholder="식단에 대한 설명을 적어주세요"
          ></b-form-textarea>
        </b-form-group>
        <b-form-group
          label="단백질"
          label-for="protein"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input id="protein" v-model="protein"></b-form-input>
        </b-form-group>
        <b-form-group
          label="탄수화물"
          label-for="carb"
          label-cols-xl="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input id="carb" v-model="carb"></b-form-input>
        </b-form-group>
        <b-form-group
          label="지방"
          label-for="fat"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input id="fat" v-model="fat"></b-form-input>
        </b-form-group>
        <div style="text-align:center">
        <b-button variant="outline-success" @click="createMeal">등록</b-button>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "MealCreate",
  data() {
    return {
      title: "",
      imgFilePath: null,
      content: "",
      protein: 0,
      carb: 0,
      fat: 0,
    };
  },
  methods: {
    createMeal() {
      let newMeal = {
        post_no: 0,
        title: this.title,
        filepath: this.imgFilePath,
        user_no: this.$store.state.user_no,
        content: this.content,
        protein: this.protein,
        carb: this.carb,
        fat: this.fat,
      };
      this.$store.dispatch("createMeal", newMeal);
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
h1 {
  font-family: "Do Hyeon", sans-serif;
  margin: 30px;
}
</style>
