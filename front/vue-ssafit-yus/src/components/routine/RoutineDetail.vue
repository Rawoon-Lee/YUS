<template>
  <div>
    <h2>루틴 상세 내용</h2>
    <div class="m-4" v-if="routine">
      <div class="container">
        <div>
          <b-button to="/routine">목록으로 돌아가기</b-button>
        </div>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">제목</th>
              <th scope="col">설명</th>
              <th scope="col">작성자</th>
              <th scope="col">작성일</th>
              <th scope="col">조회수</th>
              <th scope="col">좋아요 수</th>
              <th scope="col">좋아요</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{ routine.title }}</td>
              <td>{{ routine.content }}</td>
              <td>{{ routine.userId }}</td>
              <td>{{ routine.regDate }}</td>
              <td>{{ routine.viewCnt }}</td>
              <td>{{ routine.likedCnt }}</td>
              <td>
                <button v-show="!isRouLiked" type="button" @click="addLike">
                  &#128153;
                </button>
                <button v-show="isRouLiked" type="button" @click="delLike">
                  &#128150;
                </button>
              </td>
            </tr>
          </tbody>
        </table>
        <div>
          <v-timeline dense dark>
            <v-timeline-item
              v-for="(workout, index) in workouts"
              :key="index"
              light
              fill-dot
            >
              <span slot="opposite">{{ workout.orderNo }}</span>
              <v-card class="elevation-2">
                <v-card-title class="text-h5">
                  {{ workout.exerciseName }}
                </v-card-title>
                <v-card-text> set: {{ workout.routineSet }} </v-card-text>
                <v-card-text> rep: {{ workout.routineRep }} </v-card-text>
              </v-card>
            </v-timeline-item>
          </v-timeline>
        </div>
      </div>
      <!-- <routine-detail-exercise
        v-for="(workout, index) in workouts"
        :key="index"
        :exerciseName="workout.exerciseName"
        :rep="workout.routineSet"
        :set="workout.routineRep"
      ></routine-detail-exercise> -->
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
// import RoutineDetailExercise from "@/components/routine/RoutineDetailExercise.vue";

export default {
  name: "RoutineDetail",
  data() {
    return {
      routineId: null,
    };
  },
  // components: {
  //   RoutineDetailExercise,
  // },
  computed: {
    ...mapState(["routine"]),
    ...mapState(["workouts"]),
    ...mapState(["isRouLiked"]),
    ...mapState(["commsYou"]),
  },
  created() {
    const pathName = this.$route.path.split("/");
    const id = pathName[pathName.length - 1];
    console.log("??? created");
    this.$store.dispatch("getRoutine", id);
    this.calRoutine(id);
    this.routineId = id;
    let liked = {
      userId: sessionStorage.getItem("USER_ID"),
      routineNo: this.routineId,
    };
    this.$store.dispatch("getIsLikedRou", JSON.stringify(liked));
    console.log(5);
  },
  methods: {
    calRoutine(routineId) {
      console.log(2);
      this.$store.dispatch("getRoutine", routineId);
      console.log(this.routine);
      console.log(4);
    },
    addLike() {
      let liked = {
        userId: sessionStorage.getItem("USER_ID"),
        routineNo: this.routineId,
      };
      this.$store.dispatch("addLikedRou", JSON.stringify(liked));
      this.calRoutine(this.routineId);
    },
    delLike() {
      let liked = {
        userId: sessionStorage.getItem("USER_ID"),
        routineNo: this.routineId,
      };
      this.$store.dispatch("delLikedRou", JSON.stringify(liked));
      this.calRoutine(this.routineId);
    },
  },
};
</script>

<style scoped>
button {
  border: 0;
  background-color: rgba(0, 0, 0, 0);
}
</style>
