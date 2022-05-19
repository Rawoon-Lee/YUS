<template>
  <div class="container">
    <h2>루틴 목록</h2>
    <hr />
    <div v-if="routines.length">
      <b-table-simple hover responsive class="text-center">
        <b-thead>
          <b-tr>
            <b-th>번호</b-th>
            <b-th>제목</b-th>
            <b-th>글쓴이</b-th>
            <b-th>운동 분류</b-th>
            <b-th>등록일</b-th>
            <b-th>조회수</b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <b-tr v-for="(rout, index) in pageRoutinesList" :key="index">
            <b-td>{{ index + 1 }}</b-td>
            <b-td>
              <b-link :to="`/routine/${rout.routineNo}`">{{
                rout.title
              }}</b-link></b-td
            >
            <b-td>{{ rout.userNo }}</b-td>
            <b-td>{{ exTypes[rout.exerciseType] }}</b-td>
            <b-td>{{ rout.regDate }}</b-td>
            <b-td>{{ rout.viewCnt }}</b-td>
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
  name: "MealList",
  data() {
    return {
      keyword: "",
      mode: 1,
      currentPage: 1,
      perPage: 10,
      exTypes: ["헬스", "홈트레이닝"],
    };
  },
  computed: {
    ...mapState(["routines"]),
    rows() {
      return this.routines.length;
    },
    pageRoutinesList() {
      return this.routines.slice(
        (this.currentPage - 1) * this.perPage,
        this.currentPage * this.perPage
      );
    },
  },
  created() {
    this.$store.dispatch("getRoutines");
  },
  methods: {
    search() {
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch("getRoutines", payload);
    },
  },
};
</script>

<style></style>
