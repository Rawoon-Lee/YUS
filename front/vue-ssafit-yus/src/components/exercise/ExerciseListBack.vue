<template>
  <div class="container">
    <h2>운동 정보 목록</h2>
    <hr />
    <div v-if="videos.length">
      <b-table-simple hover responsive class="text-center">
        <b-thead>
          <b-tr>
            <b-th>번호</b-th>
            <b-th>제목</b-th>
            <b-th>유튜버</b-th>
            <b-th>부위</b-th>
            <b-th>좋아요</b-th>
            <b-th>조회수</b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <b-tr v-for="(ex, index) in pageVideosList" :key="index">
            <b-td>{{ index + 1 }}</b-td>
            <b-td>
              <b-link :to="`/exercise/detail/${ex.videoId}`">{{
                ex.title
              }}</b-link></b-td
            >
            <b-td>{{ ex.channelName }}</b-td>
            <b-td>{{ ex.exerciseName }}</b-td>
            <b-td>{{ ex.LikedCnt }}</b-td>
            <b-td>{{ ex.viewCnt }}</b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
    </div>
    <div v-else>등록된 게시글이 없습니다.</div>
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
      rows: null,
      pageVideosList: [],
      options: ["렛풀다운", "시티드 케이블 로우", "바벨 로우"],
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
