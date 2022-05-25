<template>
  <div>
    <h2>동영상 상세보기</h2>
    <div class="m-4" v-if="video">
      <div>
        <b-embed
          type="iframe"
          aspect="16by9"
          :src="`https://www.youtube.com/embed/${videoId}`"
          allowfullscreen
        ></b-embed>
      </div>
      <div class="container">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">제목</th>
              <th scope="col">유튜버</th>
              <th scope="col">운동</th>
              <th scope="col">조회수</th>
              <th scope="col">좋아요 수</th>
              <th scope="col">좋아요</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{ video.title }}</td>
              <td>{{ video.channelName }}</td>
              <td>{{ video.exerciseName }}</td>
              <td>{{ video.viewCnt }}</td>
              <td>{{ video.LikedCnt }}</td>
              <td>
                <button v-show="!isYouLiked" type="button" @click="addLike">
                  &#128153;
                </button>
                <button v-show="isYouLiked" type="button" @click="delLike">
                  &#128150;
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div>
        <b-button to="/exercise/all">목록으로 돌아가기</b-button>
      </div>
    </div>
    <div v-if="commsYou">
      <comment-box
        v-for="(comm, index) in commsYou"
        :key="index"
        :comm="comm.comm"
        :userId="comm.userId"
        :regDate="comm.regDate"
        :classNo="comm.classNo"
      ></comment-box>
      {{ commsYou[0] }}
    </div>
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
.iconBtn {
  color: pink;
  border: 0;
  background-color: rgba(0, 0, 0, 0);
}
</style>
