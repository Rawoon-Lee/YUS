<template>
  <div class="container">
    <h2 style="margin-top: 2rem">챌린지 그룹 목록</h2>
    <hr />
    <div style="text-align: right; margin-bottom: 1rem">
      <b-button to="challenge/create">그룹 만들기</b-button>
    </div>
    <div v-if="groups.length">
      <b-table-simple hover responsive class="text-center">
        <b-thead>
          <b-tr>
            <b-th>번호</b-th>
            <b-th>그룹명</b-th>
            <b-th>그룹장</b-th>
            <b-th>멤버</b-th>
            <b-th>Activity Credit</b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <b-tr v-for="(group, index) in pageGroupsList" :key="index">
            <b-td>{{ index + 1 }}</b-td>
            <b-td>
              <b-link :to="`/challenge/detail/${group.groupNo}`">{{
                group.groupName
              }}</b-link></b-td
            >
            <b-td>{{ group.userId }}</b-td>
            <b-td>{{ group.currentPeople }} / {{ group.maximumPeople }}</b-td>
            <b-td>{{ group.groupPoint }}</b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
    </div>
    <div>
      <b-form-select
        v-model="mode"
        style="
          width: 100px;
          height: 40px;
          margin-bottom: 0.5rem;
          margin-right: 0.5rem;
        "
      >
        <option value="1">제목 :</option>
        <option value="2">내용 :</option>
        <option value="3">제목+내용 :</option>
      </b-form-select>
      <input
        type="text"
        v-model="keyword"
        style="
          border: 1px solid;
          border-radius: 4px;
          margin-top: 0.5rem;
          height: 40px;
        "
      />
      <button
        @click="search"
        style="
          margin-left: 1rem;
          margin-bottom: 1rem;
          color: gray;
          border: 1px solid;
          width: 60px;
          border-radius: 5px;
          height: 40px;
        "
      >
        검색
      </button>
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
      userId: sessionStorage.getItem("USER_ID"),
    };
  },
  computed: {
    ...mapState(["groups"]),
    ...mapState(["userInfo"]),
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
    let userId = sessionStorage.getItem("USER_ID");
    this.$store.dispatch("getGroups");
    this.$store.dispatch("getUserInfo", userId);
  },
  methods: {
    search() {
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch("getGroups", payload);
    },
    enroll(groupNo) {
      let user = {
        userId: this.userId,
        groupNo: groupNo,
      };
      console.log(JSON.stringify(user));
      this.$store.dispatch("enrollGroup", JSON.stringify(user));
    },
  },
};
</script>

<style></style>
