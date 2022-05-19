<template>
  <div class="container">
    <h2>운동 정보 목록</h2>
    <hr />
    <div v-if="exercises.length">
      <b-table-simple hover responsive class="text-center">
        <b-thead>
          <b-tr>
            <b-th>번호</b-th>
            <b-th>제목</b-th>
            <b-th>부위</b-th>
            <b-th>찜</b-th>
            <b-th>조회수</b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <b-tr v-for="meal in pageBoardList" :key="meal.post_no">
            <b-td>{{ meal.post_no }}</b-td>
            <b-td>
              <b-link :to="`/meal/${meal.post_no}`">{{
                meal.title
              }}</b-link></b-td
            >
            <b-td>{{ meal.user_no }}</b-td>
            <b-td>{{ meal.carb * 3 + meal.protein * 2 + meal.fat * 4 }}</b-td>
            <b-td>{{ meal.reg_date }}</b-td>
            <b-td>{{ meal.view_cnt }}</b-td>
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
    ...mapState(["exercises"]),
    rows() {
      return this.exercises.length;
    },
    pageExerciseList() {
      return this.exercises.slice(
        (this.currentPage - 1) * this.perPage,
        this.currentPage * this.perPage
      );
    },
  },
  created() {
    this.$store.dispatch("getExercises");
  },
  methods: {
    search() {
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch("getExercises", payload);
    },
  },
};
</script>

<style></style>
