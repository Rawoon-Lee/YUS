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
      <div class="card">
        <routine-detail-exercise
          v-for="(workout, index) in workouts"
          :key="index"
          :exerciseName="workout.exerciseName"
          :rep="workout.routineSet"
          :set="workout.routineRep"
        ></routine-detail-exercise>
      </div>
      <div>
        <button
          type="button"
          class="btn btn-secondary"
          v-show="keys.includes(userId)"
          @click="success"
        >
          운동 달성!
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import ChallengeDetailUser from "@/components/challenge/ChallengeDetailUser.vue";

export default {
  data() {
    return {
      userId: sessionStorage.getItem("USER_ID"),
      groupNo: null,
    };
  },
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
    this.groupNo = id;
  },
  methods: {
    success() {
      let user = {
        userId: this.userId,
      };
      this.$store.dispatch("success", JSON.stringify(user));
      this.$store.dispatch("getGroup", this.groupNo);
    },
  },
};
</script>
