<template>
  <div>
    <div class="d-flex justify-content-center flex-wrap .container-md">
      <div v-for="video in pageVideosList" :key="video.videoId">
          <!-- 카드 -->
          <div class="card h-200" style="width: 370px; margin-right:2rem">
            <iframe
              :src="`https://www.youtube.com/embed/${video.videoId}`"
            ></iframe>
            <div class="card-body">
              <h5 class="videotitle">
                <b-link style="color: #646464" :to="`/exercise/detail/${video.videoId}`">
                {{video.title}}
                </b-link>
              </h5>
              <div style="text-align:right; font-weight: bold;">{{ video.channelName }}</div>
              <div class="videotext" style="text-align:right">
                <div><b-icon-eye-fill></b-icon-eye-fill> {{ video.viewCnt }}</div>
                <div><b-icon icon="heart-fill" style="color: red"></b-icon> {{ video.LikedCnt }}</div>
              </div>
            </div>
          </div>
          <div
            class="d-flex justify-content-start flex-wrap"
            style="margin: 3%"
          ></div>
      </div>
    </div>

    <b-pagination
      page-class="customPage"
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
      perPage: 10,
      rows: null,
      pageVideosList: [],
      options: ["스쿼트", "레그 프레스", "레그 익스텐션"],
      videoFianl: [],
    };
  },
  computed: {
    ...mapState(["videos"]),
  },
  created() {
    for (let i = 0; i < this.videos.length; i++) {
      if (this.options.includes(this.videos[i].exerciseName)) {
        this.videoFianl.push(this.videos[i]);
      }
    }
    this.rows = this.videoFianl.length;
    this.pageVideosList = this.videoFianl.slice(
      (this.currentPage - 1) * this.perPage,
      this.currentPage * this.perPage
    );
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
