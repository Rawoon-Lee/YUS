<template>
  <div class="container">
  <br>
    <h1 style="text-align:center">루틴 등록</h1>
    <hr>
    <!-- {{ userid }} -->
    {{ USER_ID }}
    <b-card bg-variant="light">
      <div class="m-4">
        <b-form-group
          label="제목"
          label-for="input-1"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input
            id="input-1"
            v-model="title"
            trim
            placeholder="제목을 입력해주세요"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          label="설명"
          label-for="input-2"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input
            id="input-2"
            v-model="content"
            trim
            placeholder="루틴에 대한 설명을 적어주세요"
          ></b-form-input>
        </b-form-group>
        <hr>
        <routine-create-health-workout
          v-for="(item, index) in workouts"
          :key="index"
          @upload="addEx"
        ></routine-create-health-workout>
        <button
          :disabled="!isChecked"
          type="button"
          @click="add"
          style="border:1px solid; color:gray; border-radius:5px; padding:5px 5px 5px 5px"
        >
          >루틴 더 추가하기
        </button>
        <br />
        <br />
        <div style="text-align:center">
        <b-button variant="success" @click="createRoutine"
          >등록</b-button
        ></div>
      </div>
    </b-card>
  </div>
</template>

<script>
import RoutineCreateHealthWorkout from "@/components/routine/RoutineCreateHealthWorkout.vue";
import { mapState } from "vuex";
export default {
  data() {
    return {
      // user_id:,
      title: null,
      content: null,
      workouts: [RoutineCreateHealthWorkout],
      routines: [],
      isChecked: false,
      order: 0,
    };
  },
  methods: {
    add() {
      this.workouts.push(RoutineCreateHealthWorkout);
      this.isChecked = false;
    },
    addEx(exercise) {
      this.order++;
      exercise.orderNo = this.order;
      console.log(this.order);
      this.routines.push(exercise);
      this.isChecked = true;
      console.log(this.routines);
    },
    createRoutine() {
      let Routine = {
        // routineNo: 0,
        userId: sessionStorage.getItem("USER_ID"),
        title: this.title,
        content: this.title,
      };
      let newRoutine = {
        routineInfo: Routine,
        eprlist: this.routines,
      };
      // this.routines.unshift(newRoutine);
      let JSONRoutine = JSON.stringify(newRoutine);
      console.log(JSON.stringify(newRoutine));
      // newRoutine = encodeURI(newRoutine)
      this.$store.dispatch("createRoutine", JSONRoutine);
    },
  },
  components: {
    RoutineCreateHealthWorkout,
  },
  computed: {
    ...mapState(["USER_ID"]),
  },
};
</script>

<style></style>
