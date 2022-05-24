<template>
  <div class="container">
    <h2>챌린지 그룹 목록</h2>
    <b-button to="challenge/create">그룹 만들기</b-button>
    <hr />
    <div v-if="groups.length">
      <b-table-simple hover responsive class="text-center">
        <b-thead>
          <b-tr>
            <b-th>번호</b-th>
            <b-th>그룹명</b-th>
            <b-th>그룹장</b-th>
            <b-th>멤버</b-th>
            <b-th>Activity Credit</b-th>
            <b-th>가입 신청</b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <b-tr v-for="group in pageGroupsList" :key="group.groupNo">
            <b-td>{{ group.groupNo }}</b-td>
            <b-td>
              <b-link :to="`/challenge/detail/${group.groupNo}`">{{
                group.groupName
              }}</b-link></b-td
            >
            <b-td>포켓몬빵순이</b-td>
            <b-td>{{ group.currentPeople }} / {{ group.maximumPeople }}</b-td>
            <b-td>{{ group.groupPoint }}</b-td>
            <b-td><b-button @click="enroll">가입 신청</b-button></b-td>
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
    ...mapState(["groups"]),
    rows() {
      return this.groups.length;
    },
    pageGroupsList() {
      return this.groups.slice(
        (this.currentPage - 1) * this.perPage,
        this.currentPage * this.perPage
      );
    },
  },
  created() {
    this.$store.dispatch("getGroups");
  },
  methods: {
    search() {
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch("getGroups", payload);
    },
    enroll() {
      //등록하는 함수
    },
  },
};
</script>

<style></style>
