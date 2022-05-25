<template>
  <div>
  <br>
    <h2 style="text-align:center">루틴 상세 내용</h2>
    <hr>
    <div style="text-align:left; margin-left:10px margin-bottom:1rem">
      <b-button to="/routine" style="margin-left:5rem">목록</b-button>
    </div>
    <div class="m-4" v-if="routine">
      <div style="width:90%; height:700px; margin:auto; border:1px solid gray; border-radius:10px">
        <div style="display:flex; justify-content:space-between; text-align:center">
        <div align="left" style="width:50%; margin-left:3rem">
        <br><br>
        <h3 style="text-align:center">내용</h3>
        <hr>
          <table class="table" style="text-align:center">
              <tr>
                <td>제목 : {{ routine.title }}</td>
              </tr>
              <tr>
                <td> {{ routine.userId }} / {{ routine.regDate }} </td>
              </tr>
              <tr>
                <td> 조회수 : {{ routine.viewCnt }} | 좋아요 : {{ routine.likedCnt }} </td>
              </tr>
              <tr>
                <td>내용 : {{ routine.content }}</td>
              </tr>
          </table>
          <div style="text-align:center">
              <button v-show="!isRouLiked" type="button" @click="addLike">
                &#128153;
              </button>
              <button v-show="isRouLiked" type="button" @click="delLike">
                &#128150;
               </button>
           </div>
        </div>
        <div align="right" style="width:40%; margin:auto">
        <br><br>
        <h3 style="text-align:center">루틴</h3>
        <hr>
        <div style="margin:auto">
          <routine-detail-exercise
            v-for="(workout, index) in workouts"
            :key="index"
            :exerciseName="workout.exerciseName"
            :rep="workout.routineSet"
            :set="workout.routineRep"
          ></routine-detail-exercise>
          </div>
        </div>
        </div>
        <!-- <div>
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
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import RoutineDetailExercise from "@/components/routine/RoutineDetailExercise.vue";

export default {
  name: "RoutineDetail",
  data() {
    return {
      routineId: null,
    };
  },
  components: {
    RoutineDetailExercise,
  },
  computed: {
    ...mapState(["routine"]),
    ...mapState(["workouts"]),
    ...mapState(["isRouLiked"]),
    ...mapState(["commsYou"]),
  },
  created() {
    const pathName = this.$route.path.split("/");
    const id = pathName[pathName.length - 1];
    this.$store.dispatch("getRoutine", id);
    this.calRoutine(id);
    this.routineId = id;
    let liked = {
      userId: sessionStorage.getItem("USER_ID"),
      routineNo: this.routineId,
    };
    this.$store.dispatch("getIsLikedRou", JSON.stringify(liked));
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
