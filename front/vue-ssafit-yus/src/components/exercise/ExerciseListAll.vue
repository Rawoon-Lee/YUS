<template>
  <div>
    <h2>운동 정보 목록</h2>
    <v-simple-table>
      <hr />
      <template v-if="videos.length">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>유튜버</th>
            <th>운동</th>
            <th>좋아요</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(ex, index) in pageVideosList" :key="index">
            <td>{{ index + 1 }}</td>
            <td>
              <b-link :to="`/exercise/detail/${ex.videoId}`">{{
                ex.title
              }}</b-link>
            </td>
            <td>{{ ex.channelName }}</td>
            <td>{{ ex.exerciseName }}</td>
            <td>{{ ex.LikedCnt }}</td>
            <td>{{ ex.viewCnt }}</td>
          </tr>
        </tbody>
      </template>
      <div v-else>등록된 게시글이 없습니다.</div>
    </v-simple-table>
    <div>
      <select v-model="mode">
        <option value="1">제목</option>
        <option value="2">내용</option>
        <option value="3">제목+내용</option>
      </select>
      <input type="text" v-model="keyword" />
      <button @click="search">검색</button>
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
      perPage: 10,
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
