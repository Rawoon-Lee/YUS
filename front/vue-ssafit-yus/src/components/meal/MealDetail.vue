<template>
  <div
    class="container"
    style="border: 1px solid #282828; border-radius: 10px; margin-top: 1rem"
  >
    <h2>식단 상세 페이지</h2>
    <div
      style="
        vertical-align: middle;
        background-color: #323232;
        color: aliceblue;
        height: 70px;
        border: 1px solid black;
        margin-top: 1rem;
      "
    >
      <div
        style="vertical-align: middle; margin-top: 0.8rem; text-align: center"
      >
        <span
          style="
            ont-size: 18pt;
            padding: 5px 5px 5px 5px;
            background-color: #828282;
            font-weight: bold;
            border-radius: 7px;
            margin-left: 0.3rem;
          "
          >{{ meal.userId }}</span
        >
        <span style="margin-left: 0.3rem; font-size: 20pt">{{
          meal.title
        }}</span>
      </div>
    </div>
    <hr />
    <div style="display: flex; justify-content: space-between">
      <div align="left" style="margin-left: 1rem">제목 : {{ meal.title }}</div>
      <div align="right">
        <span style="margin-right: 1rem"
          ><b-icon-eye-fill></b-icon-eye-fill> {{ meal.viewCnt }}</span
        >
        <span style="margin-right: 1rem"
          ><b-icon icon="heart-fill" style="color: red"></b-icon>
          {{ meal.LikedCnt }}</span
        >
        <span
          ><b-icon icon="chat-right-text-fill"></b-icon>
          {{ commsMeal.length }}</span
        >
      </div>
    </div>
    <hr />
    <div>
      <div>
        <img
          :src="require(`@/assets/MealBoard/${meal.filepath}.png`)"
          alt="식단사진"
          class="meal"
        />
      </div>
      <div class="card">
        {{ meal.content }}
      </div>
    </div>
    <hr />
    <div class="row justify-content-center">
      <b-button
        v-show="!isMealLiked"
        type="button"
        @click="addLike"
        variant="light"
      >
        <b-icon icon="heart-fill" style="color: red"></b-icon> 좋아요
      </b-button>
      <b-button
        v-show="isMealLiked"
        type="button"
        @click="delLike"
        variant="light"
      >
        <b-icon icon="heart"></b-icon> 취소
      </b-button>
    </div>
    <hr />
    <!-- <div v-if="commsMeal.length" style="margin-left: 2rem">
      <div
        v-for="(comm, index) in commsMeal"
        :key="index"
        :comm="comm.comm"
        :userId="comm.userId"
        :regDate="comm.regDate"
        :classNo="comm.classNo"
        :commGroup="comm.commGroup"
      >
        <div class="row" v-if="comm.classNo == 0" style="margin-top: 1rem">
          <div class="col">{{ comm.comm }}</div>
          <div class="col">{{ comm.userId }}</div>
          <div class="col">{{ comm.regDate }}</div>
          <div style="margin-right: 5rem; width: 10%">
            <b-button type="button" @click="addLike" variant="light">
              <b-icon icon="reply-fill"></b-icon>답글
            </b-button>
          </div>
        </div>
        <div class="row" style="margin-left: 2px; margin-top: 0.5rem" v-else>
          <div><b-icon icon="arrow-return-right"></b-icon></div>
          <div class="col">{{ comm.comm }}</div>
          <div class="col">{{ comm.userId }}</div>
          <div class="col">{{ comm.regDate }}</div>
          <div style="margin-right: 5rem">
            <b-button type="button" @click="addLike" variant="light">
              <b-icon icon="reply-fill"></b-icon>답글
            </b-button>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <h2
        style="
          margin-left: 1rem;
          color: gray;
          font-size: 20pt;
          text-align: center;
        "
      >
        작성된 댓글이 없습니다.
      </h2>
    </div>
    <hr />
    <div>
      <b-icon icon="chat-left-text-fill" style="margin-top: 1rem"></b-icon> 댓글
      작성
    </div> -->
    <br />
    <meal-comment-form></meal-comment-form>
    <hr />
    <div
      class="row justify-content-center"
      style="margin-left: 2px; margin-right: 5px"
    >
      <b-button to="/exercise/all">목록으로 돌아가기</b-button>
    </div>
    <hr />
    <br />
  </div>
</template>

<script>
import { mapState } from "vuex";
import MealCommentForm from "@/components/comments/MealCommentForm.vue";

export default {
  name: "MealDetail",
  data() {
    return {
      userId: sessionStorage.getItem("USER_ID"),
      postNo: null,
    };
  },
  components: {
    MealCommentForm,
  },
  computed: {
    ...mapState(["commsMeal"]),
    ...mapState(["meal"]),
    ...mapState(["isMealLiked"]),
  },
  created() {
    const pathName = this.$route.path.split("/");
    const id = pathName[pathName.length - 1];
    console.log("???" + id);
    this.$store.dispatch("getMeal", id);
    // this.$store.dispatch("getCommentsYou", id);
    this.postNo = id;
    let liked = {
      userId: sessionStorage.getItem("USER_ID"),
      postNo: this.postNo,
    };
    this.$store.dispatch("getIsLikedMeal", JSON.stringify(liked));
  },
  mounted() {
    let user = sessionStorage.getItem("USER_ID");
    if (user != this.meal.userId) {
      let post = {
        postNo: this.postNo,
      };
      let JSONRou = JSON.stringify(post);
      console.log(JSONRou);
      this.$store.dispatch("addViewMeal", this.postNo);
    }
  },
  methods: {
    calMeal(postNo) {
      this.$store.dispatch("getMeal", postNo);
    },
    addLike() {
      let liked = {
        userId: sessionStorage.getItem("USER_ID"),
        postNo: this.postNo,
      };
      let JSONliked = JSON.stringify(liked);
      console.log(JSONliked);
      this.$store.dispatch("addLikedMeal", JSONliked);
      this.calMeal(this.postNo);
    },
    delLike() {
      let liked = {
        userId: sessionStorage.getItem("USER_ID"),
        postNo: this.postNo,
      };
      let JSONliked = JSON.stringify(liked);
      console.log(JSONliked);
      this.$store.dispatch("delLikedMeal", JSONliked);
      this.calMeal(this.postNo);
    },
  },
};
</script>

<style scoped>
.meal {
  width: 300px;
}
</style>
