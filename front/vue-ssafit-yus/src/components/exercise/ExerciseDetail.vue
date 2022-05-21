<template>
  <div>
    <h2>동영상 상세보기</h2>
    <div class="m-4">
      <div>
        <b-embed
          type="iframe"
          aspect="16by9"
          :src="`https://www.youtube.com/embed/${video.videoId}`"
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
              <th scope="col">부위</th>
              <th scope="col">조회수</th>
              <th scope="col">좋아요 수</th>
              <th scope="col">좋아요</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{ video.title }}</td>
              <td>{{ video.channelName }}</td>
              <td>{{ video.workout }}</td>
              <td>{{ video.part }}</td>
              <td>{{ video.viewCnt }}</td>
              <td>{{ video.liked }}</td>
              <td>
                <button :disabled="isLiked" type="button" @click="like">
                  &#128147;
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
      video: null,
      //liked: null;
    };
  },
  computed: {
    ...mapState(["exercises"]),
    ...mapState(["videos"]),
    ...mapState(["isLiked"]),
  },
  created() {
    // this.$store.dispatch("createVideosForUse");
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    let videosUseful = [];
    for (let video of this.videos) {
      for (let ex of this.exercises) {
        if (video.exerciseNo == ex.exerciseNo) {
          let temp = {
            title: video.title,
            videoId: video.videoId,
            channelName: video.channelName,
            part: ex.exercisePart,
            workout: ex.exerciseName,
            viewCnt: video.viewCnt,
            liked: -1,
          };
          // console.log("나 일단 만들어지긴 했음");
          // console.log(temp);
          videosUseful.push(temp);
        }
      }
    }
    for (let video of videosUseful) {
      if (video.videoId == id) {
        this.video = video;
        break;
      }
    }
    this.$store.dispatch("getIsLiked");
  },
  methods: {
    like() {
      console.log(this.USER_Id);
      console.log("버튼 눌렸다");
    },
  },
};
</script>

<style>
button {
  border: 0;
  background-color: rgba(0, 0, 0, 0);
}
</style>
