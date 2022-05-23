<template>
  <div class="container">
    <h1>루틴 등록</h1>
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
        <routine-create-health-workout
          v-for="(item, index) in workouts"
          :key="index"
          @upload="addEx"
        ></routine-create-health-workout>
        <button
          :disabled="!isChecked"
          type="button"
          class="btn btn-dark"
          @click="add"
        >
          루틴 추가
        </button>
        <br />
        <br />
        <b-button variant="outline-success" @click="createRoutine"
          >등록</b-button
        >
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
