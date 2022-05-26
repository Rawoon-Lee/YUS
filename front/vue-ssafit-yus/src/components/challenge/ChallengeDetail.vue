<template>
  <div>
    <div class="card" style="display: flex; align-items: center; border: none">
      <div class="card-body" style="float: left">
        <h3>그룹명 : {{ group.groupName }}</h3>
        <p class="card-text">그룹 점수 : {{ group.groupPoint }}</p>
        <p class="card-text">
          그룹 인원 : {{ group.currentPeople }} / {{ group.maximumPeople }}
        </p>
        <p class="card-text">방장 : {{ group.userId }}</p>
      </div>
      <h4>최근 일주일간의 운동 기록</h4>
      <div
        class="card"
        style="width: 25rem; display: flex; align-items: center"
      >
        <ul class="list-group list-group-flush">
          <li
            class="list-group-item"
            v-for="(member, index) in keys"
            :key="index"
            style=""
          >
            <challenge-detail-user
              :member="groupMem[member]"
              :user="member"
            ></challenge-detail-user>
          </li>
        </ul>
      </div>
    </div>
    <!-- <challenge-detail-user
      v-for="(member, index) in keys"
      :key="index"
      :member="groupMem[member]"
      :user="member"
    ></challenge-detail-user> -->
  </div>
</template>

<script>
import { mapState } from "vuex";
import ChallengeDetailUser from "@/components/challenge/ChallengeDetailUser.vue";

export default {
  computed: {
    ...mapState(["groupMem"]),
    ...mapState(["group"]),
    ...mapState(["keys"]),
  },
  components: {
    ChallengeDetailUser,
  },
  created() {
    let pathname = this.$route.path.split("/");
    let id = pathname[pathname.length - 1];
    this.$store.dispatch("getGroup", id);
  },
};
</script>
