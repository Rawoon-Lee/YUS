<template>
  <div
    class="container"
    style="border: 1px solid #282828; border-radius: 10px; margin-top: 1rem"
  >
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
          >{{ video.exerciseName }}</span
        >
        <span style="margin-left: 0.3rem; font-size: 20pt">{{
          video.title
        }}</span>
      </div>
    </div>
    <hr />
    <div style="display: flex; justify-content: space-between">
      <div align="left" style="margin-left: 1rem">
        채널명 : {{ video.channelName }}
      </div>
      <div align="right">
        <span style="margin-right: 1rem"
          ><b-icon-eye-fill></b-icon-eye-fill> {{ video.viewCnt }}</span
        >
        <span style="margin-right: 1rem"
          ><b-icon icon="heart-fill" style="color: red"></b-icon>
          {{ video.LikedCnt }}</span
        >
        <span
          ><b-icon icon="chat-right-text-fill"></b-icon>
          {{ commsYou.length }}</span
        >
      </div>
    </div>
    <hr />
    <div>
      <b-embed
        type="iframe"
        aspect="16by9"
        :src="`https://www.youtube.com/embed/${videoId}`"
        allowfullscreen
      ></b-embed>
    </div>
    <hr />
    <div class="row justify-content-center">
      <b-button
        v-show="!isYouLiked"
        type="button"
        @click="addLike"
        variant="light"
      >
        <b-icon icon="heart-fill" style="color: red"></b-icon> 좋아요
      </b-button>
      <b-button
        v-show="isYouLiked"
        type="button"
        @click="delLike"
        variant="light"
      >
        <b-icon icon="heart"></b-icon> 취소
      </b-button>
    </div>
    <hr />
    <div v-if="commsYou.length" style="margin-left: 2rem">
      <div v-for="(comm, index) in commsYou" :key="index">
        <div class="row" v-if="comm.classNo == 0" style="margin-top: 1rem">
          <div class="col">{{ comm.comm }}</div>
          <div class="col">{{ comm.userId }}</div>
          <div class="col">{{ comm.regDate }}</div>
          <div class="col">
            <b-button type="button" @click="toggleShow(index)" variant="light">
              <b-icon icon="reply-fill"></b-icon>답글
            </b-button>
            <b-button
              v-if="comm.userId == userId"
              type="button"
              @click="deleteComm(comm.commIndex)"
              variant="danger"
            >
              <b-icon icon="trash-fill"></b-icon>삭제
            </b-button>
          </div>
        </div>
        <div class="row" style="margin-left: 12px; margin-top: 0.5rem" v-else>
          <div><b-icon icon="arrow-return-right"></b-icon></div>
          <div class="col">{{ comm.comm }}</div>
          <div class="col">{{ comm.userId }}</div>
          <div class="col">{{ comm.regDate }}</div>
          <div class="col">
            <b-button type="button" @click="toggleShow(index)" variant="light">
              <b-icon icon="reply-fill"></b-icon>답글
            </b-button>
            <b-button
              v-if="comm.userId == userId"
              type="button"
              @click="deleteComm(comm.commIndex)"
              variant="danger"
            >
              <b-icon icon="trash-fill"></b-icon>삭제
            </b-button>
          </div>
        </div>
        <div v-if="comm.status">
          <youtube-Comment-form
            :classNo="1"
            :commGroup="comm.commGroup"
            :userId="userId"
            :videoId="video.videoId"
          ></youtube-Comment-form>
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
      작성({{ commsYou.length }})
    </div>
    <br />
    {{ userId }}
    <youtube-comment-form
      :classNo="0"
      :commGroup="0"
      :userId="userId"
      :videoId="videoId"
    ></youtube-comment-form>
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
import YoutubeCommentForm from "@/components/comments/YoutubeCommentForm.vue";
export default {
  name: "ExerciseDetail",
  data() {
    return {
      userId: sessionStorage.getItem("USER_ID"),
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
      videoId: null,
    };
  },
  components: {
    YoutubeCommentForm,
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
    this.$store.dispatch("getVideo", id);
    this.$store.dispatch("getCommentsYou", id);
    this.videoId = id;
    let liked = {
      userId: sessionStorage.getItem("USER_ID"),
      videoId: id,
    };
    this.$store.dispatch("getIsLikedYou", liked);
  },
  mounted() {
    this.$store.dispatch("addViewYou", this.videoId);
  },
  methods: {
    calVideo(videoId) {
      this.$store.dispatch("getVideo", videoId);
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
    toggleShow(index) {
      this.commsYou[index].status = !this.commsYou[index].status;
    },
    deleteComm(commIndex) {
      let youtubeComm = {
        commIndex: commIndex,
        videoId: this.videoId,
      };
      this.$store.dispatch("delYoutubeComm", JSON.stringify(youtubeComm));
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
