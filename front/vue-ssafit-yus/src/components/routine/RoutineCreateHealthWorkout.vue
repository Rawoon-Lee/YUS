<template>
  <div>
    <h2>운동추가</h2>
    <hr />
    <div>
      <b-form @submit.prevent>
        <b-form-group
          label="운동"
          label-for="purpose"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-select v-model="workout" :options="workouts">
            <template #first>
              <b-form-select-option :value="null" disabled
                >루틴에 추가할 운동을 선택하세요.</b-form-select-option
              >
            </template>
          </b-form-select>
        </b-form-group>
        <b-form-group
          label="set"
          label-for="set"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input id="set" v-model="set" type="number"></b-form-input>
        </b-form-group>
        <b-form-group
          label="rep"
          label-for="rep"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input id="rep" v-model="rep" type="number"></b-form-input>
        </b-form-group>
        <button :disabled="isDone" @click="upload">확인</button>
      </b-form>
    </div>
    <!-- </b-card> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      workout: null,
      set: 0,
      rep: 0,
      isDone: false,
      workouts: [
        {
          label: "어깨",
          options: [
            { value: "사이드 레터럴 레이즈", text: "사이드 레터럴 레이즈" },
            { value: "밀리터리 프레스", text: "밀리터리 프레스" },
            { value: "페이스 풀", text: "페이스 풀" },
          ],
        },
        {
          label: "가슴",
          options: [
            { value: "벤치 프레스", text: "벤치 프레스" },
            { value: "인클라인 벤치프레스", text: "인클라인 벤치프레스" },
            { value: "딥스", text: "딥스" },
            { value: "체스트 프레스", text: "체스트 프레스" },
          ],
        },
        {
          label: "등",
          options: [
            { value: "렛풀다운", text: "렛풀다운" },
            { value: "시티드 케이블 로우", text: "시티드 케이블 로우" },
            { value: "바벨 로우", text: "바벨 로우" },
          ],
        },
        {
          label: "하체",
          options: [
            { value: "스쿼트", text: "스쿼트" },
            { value: "레그 프레스", text: "레그 프레스" },
            { value: "레그 익스텐션", text: "레그 익스텐션" },
          ],
        },
      ],
    };
  },
  methods: {
    upload() {
      let exercise = {
        routineNo: 0,
        exerciseName: this.workout,
        routineSet: Number(this.set),
        routineRep: Number(this.rep),
        orderNo: 0,
      };
      this.isDone = true;
      this.$emit("upload", exercise);
    },
  },
};
</script>

<style></style>
