<template>
  <div>
    <div class="d-flex justify-content-center flex-wrap .container-md">
      <div v-for="video in pageVideosList" :key="video.videoId">
          <!-- 카드 -->
          <div class="card h-200" style="width: 310px">
            <iframe
              :src="`https://www.youtube.com/embed/${video.videoId}`"
            ></iframe>
            <div class="card-body">
              <h5 class="videotitle">
                <b-link :to="`/exercise/detail/${video.videoId}`">
                {{video.title}}
                </b-link>
              </h5>
              <div class="videotext">
                <div>{{ video.channelName }}</div>
                <div><b-icon-eye-fill></b-icon-eye-fill> : {{ video.viewCnt }}</div>
                <div><b-suit-heart-fill></b-suit-heart-fill></div>
              </div>
            </div>
          </div>
          <div
            class="d-flex justify-content-start flex-wrap"
            style="margin: 1%"
          ></div>
      </div>
    </div>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
    ></b-pagination>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "ExerciseList",
  data() {
    return {
      keyword: "",
      mode: 1,
      currentPage: 1,
      perPage: 20,
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
    ...mapState(["videos"]),
    rows() {
      return this.videos.length;
    },
    pageVideosList() {
      return this.videos.slice(
        (this.currentPage - 1) * this.perPage,
        this.currentPage * this.perPage
      );
    },
  },
  created() {
    this.$store.dispatch("getVideos");
    // this.$store.dispatch("getExercises");
  },
  methods: {
    search() {
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch("getVideos", payload);
    },
  },
};
</script>

<style></style>
