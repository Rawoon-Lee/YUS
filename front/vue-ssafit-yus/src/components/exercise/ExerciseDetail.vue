<template>
  <div class="container" style="border: 1px solid #282828">
    <div class="row align-items-center" style="background-color: #323232; color: aliceblue; height: 60px; border: 1px solid black;">
      <div class="col-md-1 align-items-center" style="background-color: #828282; font-weight: bold; border-radius: 7px; box-shadow: 2px 2px 2px black;">{{ video.exerciseName }}</div>
      <div class="col-md-11">
        <h2>{{ video.title }}</h2>
      </div>
    </div>
    <hr>
    <div class="row">
      <div class="col-md-10">{{ video.channelName }}</div>
      <div class="col-md-2">
        <span style="margin: 0.5em"><b-icon-eye-fill></b-icon-eye-fill>{{ video.viewCnt }}</span>
        <span style="margin: 0.5em"><b-icon icon="heart-fill" style="color: red"></b-icon>{{ video.LikedCnt }}</span>
        <span style="margin: 0.5em"><b-icon icon="chat-right-text-fill">{{ commsYou.lengh }}</b-icon></span>
      </div>
    </div>
    <hr>
    <div class="row">
      <b-embed
        type="iframe"
        aspect="16by9"
        :src="`https://www.youtube.com/embed/${videoId}`"
        allowfullscreen
      ></b-embed>
    </div>
    <hr>
    <div class="row justify-content-center">
      <b-button v-show="!isYouLiked" type="button" @click="addLike" variant="light">
        <b-icon icon="heart-fill" style="color: red"></b-icon> 좋아요
      </b-button>
      <b-button v-show="isYouLiked" type="button" @click="delLike" variant="light">
        <b-icon icon="heart"></b-icon> 취소
      </b-button>
    </div>
    <hr>
    <div class="row" v-if="commsYou.length">
      <comment-box
        v-for="(comm, index) in commsYou"
        :key="index"
        :comm="comm.comm"
        :userId="comm.userId"
        :regDate="comm.regDate"
        :classNo="comm.classNo"
      ></comment-box>
    </div>
    <div class="row" v-else>
      <h1>아직 등록 된 댓글이 없습니다..</h1>
    </div>
    <hr>
    <div class="row justify-content-end" style="margin-left:2px; margin-right:5px;">
        <b-button to="/exercise/all">목록으로 돌아가기</b-button>
    </div>
    <hr>
  </div>
</template>

<script>
import { mapState } from "vuex";
import CommentBox from "@/components/comments/CommentBox.vue";

export default {
  name: "ExerciseDetail",
  data() {
    return {
      videoId: null,
      videoDetail: {
        videoId: null,
        title: null,
        channelName: null,
        part: null,
        exerciseName: null,
        viewCnt: null,
        likedCnt: null,
      },
      workouts: [
        {
          label: "어깨",
          options: ["사이드 레터럴 레이즈", "밀리터리 프레스", "페이스 풀"],
        },
        {
          label: "가슴",
          options: [
            "벤치 프레스",
            "인클라인 벤치프레스",
            "딥스",
            "체스트 프레스",
          ],
        },
        {
          label: "등",
          options: ["렛풀다운", "시티드 케이블 로우", "바벨 로우"],
        },
        {
          label: "하체",
          options: ["스쿼트", "레그 프레스", "레그 익스텐션"],
        },
      ],
    };
  },
  components: {
    CommentBox,
  },
  computed: {
    // ...mapState(["exercises"]),
    ...mapState(["commsYou"]),
    ...mapState(["video"]),
    ...mapState(["isYouLiked"]),
  },
  created() {
    const pathName = this.$route.path.split("/");
    const id = pathName[pathName.length - 1];
    console.log("???" + id);
    this.$store.dispatch("getVideo", id);
    this.$store.dispatch("getCommentsYou", id);
    this.videoId = id;
    let liked = {
      userId: sessionStorage.getItem("USER_ID"),
      videoId: id,
    };
    this.$store.dispatch("getIsLikedYou", liked);
  },
  methods: {
    calVideo(videoId) {
      this.$store.dispatch("getVideo", videoId);
      // this.videoDetail.title = video.title;
      // this.videoDetail.channelName = video.channelName;
      // for (let i = 0; i < this.workouts.length; i++) {
      // if (this.workouts[i]["options"].includes(video.exerciseName)) {
      // this.videoDetail.part = this.workouts[i].label;
      // break;
      // }
      // }
      // this.videoDetail.exerciseName = video.exerciseName;
      // this.videoDetail.viewCnt = video.viewCnt;
      // this.videoDetail.likedCnt = video.LikedCnt;
    },
    addLike() {
      let liked = {
        userId: sessionStorage.getItem("USER_ID"),
        videoId: this.videoId,
      };
      let JSONliked = JSON.stringify(liked);
      console.log(JSONliked);
      this.$store.dispatch("addLikedYou", JSONliked);
      this.calVideo(this.videoId);
    },
    delLike() {
      let liked = {
        userId: sessionStorage.getItem("USER_ID"),
        videoId: this.videoId,
      };
      let JSONliked = JSON.stringify(liked);
      console.log(JSONliked);
      this.$store.dispatch("delLikedYou", JSONliked);
      this.calVideo(this.videoId);
    },
  },
};
</script>

<style scoped>
/* .iconBtn {
  color: pink;
  border: 3px;
  border-color: black;
  background-color: rgba(0, 0, 0, 0);
} */
</style>
