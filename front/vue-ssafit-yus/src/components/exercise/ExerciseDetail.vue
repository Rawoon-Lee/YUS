<template>
  <div>
    <h2>동영상 상세보기</h2>
    <div class="m-4">
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
              <th scope="col">부위</th>
              <th scope="col">운동</th>
              <th scope="col">조회수</th>
              <th scope="col">좋아요 수</th>
              <th scope="col">좋아요</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{ videoDetail.title }}</td>
              <td>{{ videoDetail.channelName }}</td>
              <td>{{ videoDetail.part }}</td>
              <td>{{ videoDetail.exerciseName }}</td>
              <td>{{ videoDetail.viewCnt }}</td>
              <td>{{ videoDetail.liked }}</td>
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
        <b-button to="/exercise">목록으로 돌아가기</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

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
        liked: null,
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
  computed: {
    // ...mapState(["exercises"]),
    ...mapState(["video"]),
    ...mapState(["isYouLiked"]),
  },
  watch: {
    video(value) {
      this.calVideo(value);
    },
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    let liked = {
      userId: sessionStorage.getItem("USER_ID"),
      videoId: id,
    };
    this.$store.dispatch("getIsLikedYou", liked);
    this.videoId = id;
    this.calVideo(this.video);
  },
  methods: {
    calVideo(video) {
      this.$store.dispatch("getVideo", this.videoId);
      this.videoDetail.title = video.title;
      this.videoDetail.channelName = video.channelName;
      for (let i = 0; i < this.workouts.length; i++) {
        if (this.workouts[i]["options"].includes(video.exerciseName)) {
          this.videoDetail.part = this.workouts[i].label;
          break;
        }
      }
      this.videoDetail.exerciseName = video.exerciseName;
      this.videoDetail.viewCnt = video.viewCnt;
      this.videoDetail.liked = video.LikedCnt;
    },
    addLike() {
      let liked = {
        userId: sessionStorage.getItem("USER_ID"),
        videoId: this.videoId,
      };
      this.$store.dispatch("addLiked", liked);
    },
    delLike() {
      let liked = {
        userId: sessionStorage.getItem("USER_ID"),
        videoId: this.videoId,
      };
      this.$store.dispatch("delLiked", liked);
    },
  },
};
</script>

<style scoped>
button {
  border: 0;
  background-color: rgba(0, 0, 0, 0);
}
</style>
