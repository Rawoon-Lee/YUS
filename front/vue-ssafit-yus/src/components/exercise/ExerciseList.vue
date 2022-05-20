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
            <b-th>찜</b-th>
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
            <b-td>체하</b-td>
            <b-td>찜</b-td>
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
    this.$store.dispatch("getExercises");
    this.$store.dispatch("createVideosForUse");
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
