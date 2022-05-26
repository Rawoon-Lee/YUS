<template>
  <div class="container">
    <div class="row"></div>
    <div style="display: flex; justify-content: space-between">
      <div align="left" style="width: 90%">
        <b-form-textarea
          no-resize
          placeholder="내용을 입력해주세요."
          v-model="comm"
        ></b-form-textarea>
      </div>
      <div align="right" class="row justify-content-end">
        <b-button
          style="height: 60%; margin-top: 0.8rem; margin-right: 3rem"
          @click="createComm()"
        >
          등록
        </b-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MealCommentForm",
  props: {
    classNo: Number,
    commGroup: Number,
    userId: String,
    postNo: Number,
  },
  data() {
    return {
      comm: "",
    };
  },
  methods: {
    createComm() {
      let mealComm = {
        userId: this.userId,
        postNo: this.postNo,
        classNo: this.classNo,
        comm: this.comm,
        orderNo: 0,
        commGroup: this.commGroup,
      };
      console.log(mealComm);
      this.$store.dispatch("addMealComm", JSON.stringify(mealComm));
    },
  },
};
</script>
