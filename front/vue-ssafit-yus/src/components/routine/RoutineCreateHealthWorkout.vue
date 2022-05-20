<template>
  <div>
    <b-alert v-model="showDuplicatedError" variant="danger" dismissible>
      이미 존재하는 아이디입니다. 다른 아이디로 시도해주세요.
    </b-alert>
    <b-alert v-model="showInternalError" variant="danger" dismissible>
      유효하지 않은 요청입니다. 올바르게 작성했는지 확인해주세요.
    </b-alert>
    <!-- <b-card bg-variant="light"> -->
    <hr />
    <div>
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
        <b-button></b-button>
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
      {{ workout }}
      {{ set }}
      {{ rep }}
    </div>
    <!-- </b-card> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: null,
      content: null,
      workouts: [
        {
          label: "어깨",
          options: [
            { value: 0, text: "사이드 레터럴 레이즈" },
            { value: 1, text: "밀리터리 프레스" },
            { value: 2, text: "페이스 풀" },
          ],
        },
        {
          label: "가슴",
          options: [
            { value: 3, text: "벤치 프레스" },
            { value: 4, text: "인클라인 벤치프레스" },
            { value: 5, text: "딥스" },
            { value: 6, text: "체스트 프레스" },
          ],
        },
        {
          label: "등",
          options: [
            { value: 7, text: "렛풀다운" },
            { value: 8, text: "시티드 케이블 로우" },
            { value: 9, text: "바벨 로우" },
          ],
        },
        {
          label: "하체",
          options: [
            { value: 10, text: "스쿼트" },
            { value: 11, text: "레그 프레스" },
            { value: 12, text: "레그 익스텐션" },
          ],
        },
      ],
      workout: null,
      set: null,
      rep: null,
      showDuplicatedError: false,
      showInternalError: false,
    };
  },
  methods: {},
};
</script>

<style></style>
