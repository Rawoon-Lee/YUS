<template>
  <div>
    <div class="d-flex justify-content-center flex-wrap .container-lg">
      <div v-for="video in pageVideosList" :key="video.videoId">
        <!-- 카드 -->
        <v-card
          class="card h-150"
          elevation="6"
          style="
            min-height: 347px;
            width: 300px;
            margin-left: 2rem;
            margin-right: 2rem;
          "
          hover
        >
          <iframe
            :src="`https://www.youtube.com/embed/${video.videoId}`"
          ></iframe>
          <div class="card-body">
            <h5 class="videotitle">
              <b-link
                style="color: #646464"
                :to="`/exercise/detail/${video.videoId}`"
              >
                {{ video.title }}
              </b-link>
            </h5>
            <div style="text-align: right; font-weight: bold">
              {{ video.channelName }}
            </div>
            <div class="videotext" style="text-align: right">
              <div><b-icon-eye-fill></b-icon-eye-fill> {{ video.viewCnt }}</div>
              <div>
                <b-icon icon="heart-fill" style="color: red"></b-icon>
                {{ video.LikedCnt }}
              </div>
            </div>
          </div>
        </v-card>
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
      perPage: 9,
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
