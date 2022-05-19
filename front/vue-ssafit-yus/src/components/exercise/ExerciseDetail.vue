<template>
  <div>
    <h2>동영상 상세보기</h2>
    <div class="m-4">
      조회수 :
      <b-badge variant="danger">{{ video.viewCnt }}</b-badge>
      <div>
        <b-embed
          type="iframe"
          aspect="16by9"
          :src="`https://www.youtube.com/embed/${video.videoId}`"
          allowfullscreen
        ></b-embed>
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
    };
  },
  computed: {
    ...mapState(["videos"]),
  },
  created() {
    this.$store.dispatch("getVideos");
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    console.log(id);
    for (let video of this.videos) {
      if (video.videoId == id) {
        this.video = video;
        break;
      }
    }
    // this.$store.dispatch("getVideo", id);
  },
  methods: {
    moveUpdate() {
      this.$router.push({ name: "boardUpdate" });
    },
    deleteBoard() {
      this.$store.dispatch("deleteBoard", this.board.id);
    },
  },
};
</script>

<style></style>
