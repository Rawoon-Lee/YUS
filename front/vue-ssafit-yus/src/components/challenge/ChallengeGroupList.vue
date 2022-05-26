<template>
  <div class="container">
    <h2 style="margin-top: 2rem">챌린지 그룹 목록</h2>
    <hr />
    <div style="text-align: right; margin-bottom: 1rem">
      <b-button to="challenge/create">그룹 만들기</b-button>
    </div>
    <div v-if="groups.length">
      <b-table-simple hover responsive class="text-center">
        <b-tbody>
          <b-tr v-for="(group, index) in pageGroupsList" :key="index">
            <b-td
              ><img
                :src="require(`@/assets/UserInfo/${group.userId}.png`)"
                alt="프로필"
                class="mypageProfile"
            /></b-td>
            <b-td>
              <div>
                <b-link :to="`/challenge/detail/${group.groupNo}`"
                  ><h2>{{ group.groupName }}</h2></b-link
                >
              </div>
              <div>리더 : {{ group.userId }}</div>
              <div>
                인원 : {{ group.currentPeople }} /
                {{ group.maximumPeople }}
              </div>
              <div>Activity Credit : {{ group.groupPoint }}</div>
            </b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
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

<style scoped>
.mypageProfile {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  object-fit: cover;
}
</style>
