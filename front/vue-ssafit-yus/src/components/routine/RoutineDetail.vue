<template>
  <div>
    <h2>동영상 상세보기</h2>
    <div class="m-4">
      <div class="container">
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
              <td>{{ routine[0].title }}</td>
              <td>{{ routine[0].content }}</td>
              <td>{{ routine[0].userId }}</td>
              <td>{{ routine[0].regDate }}</td>
              <td>{{ routine[0].viewCnt }}</td>
              <td>{{ routine[0].likedCnt }}</td>
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
      </div>
      <div>
        <b-button to="/routine">목록으로 돌아가기</b-button>
      </div>
      <routine-detail-exercise
        v-for="(workout, index) in workouts"
        :key="index"
        :exerciseName="workout.exerciseName"
        :rep="workout.routineSet"
        :set="workout.routineRep"
      ></routine-detail-exercise>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import RoutineDetailExercise from "@/components/routine/RoutineDetailExercise.vue";

const pathName = new URL(document.location).pathname.split("/");
const id = pathName[pathName.length - 1];

export default {
  name: "RoutineDetail",
  data() {
    return {
      routineId: null,
      workouts: [],
    };
  },
  components: {
    RoutineDetailExercise,
  },
  computed: {
    ...mapState(["routine"]),
    ...mapState(["isRouLiked"]),
  },
  created() {
    this.calRoutine(id);
    this.routineId = id;
    let liked = {
      userId: sessionStorage.getItem("USER_ID"),
      routineNo: this.routineId,
    };
    this.$store.dispatch("getIsLikedRou", JSON.stringify(liked));
    for (let i = 1; i < this.routine.length; i++) {
      this.workouts.push(this.routine[i]);
    }
  },
  methods: {
    calRoutine(routineId) {
      this.$store.dispatch("getRoutine", routineId);
      console.log(this.routine);
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
