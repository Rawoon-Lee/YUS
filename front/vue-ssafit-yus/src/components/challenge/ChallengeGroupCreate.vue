<template>
  <div class="container">
  <br>
    <h1 style="text-align:center">그룹 등록</h1>
    <hr>
    <b-card bg-variant="light">
      <div class="m-4">
        <b-form-group
          label="그룹명"
          label-for="input-1"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input
            id="input-1"
            v-model="title"
            trim
            placeholder="그룹명을 입력해주세요"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          label="그룹 최대 인원"
          label-for="input-2"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-input
            id="input-2"
            v-model="maxNum"
            type="number"
            trim
            placeholder="그룹에 가입 가능한 최대인원을 적어주세요"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          label="월요일"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-select v-model="routine1" :options="routineTitles">
            <template #first>
              <b-form-select-option :value="null" disabled
                >월요일에 할 루틴을 선택하세요</b-form-select-option
              >
            </template>
          </b-form-select>
        </b-form-group>
        <b-form-group
          label="화요일"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-select v-model="routine2" :options="routineTitles">
            <template #first>
              <b-form-select-option :value="null" disabled
                >화요일에 할 루틴을 선택하세요</b-form-select-option
              >
            </template>
          </b-form-select>
        </b-form-group>
        <b-form-group
          label="수요일"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-select v-model="routine3" :options="routineTitles">
            <template #first>
              <b-form-select-option :value="null" disabled
                >수요일에 할 루틴을 선택하세요</b-form-select-option
              >
            </template>
          </b-form-select>
        </b-form-group>
        <b-form-group
          label="목요일"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-select v-model="routine4" :options="routineTitles">
            <template #first>
              <b-form-select-option :value="null" disabled
                >목요일에 할 루틴을 선택하세요</b-form-select-option
              >
            </template>
          </b-form-select>
        </b-form-group>
        <b-form-group
          label="금요일"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-select v-model="routine5" :options="routineTitles">
            <template #first>
              <b-form-select-option :value="null" disabled
                >금요일에 할 루틴을 선택하세요</b-form-select-option
              >
            </template>
          </b-form-select>
        </b-form-group>
        <b-form-group
          label="토요일"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-select v-model="routine6" :options="routineTitles">
            <template #first>
              <b-form-select-option :value="null" disabled
                >토요일에 할 루틴을 선택하세요</b-form-select-option
              >
            </template>
          </b-form-select>
        </b-form-group>
        <b-form-group
          label="일요일"
          label-cols-md="2"
          label-align="left"
          label-size="lg"
        >
          <b-form-select v-model="routine7" :options="routineTitles">
            <template #first>
              <b-form-select-option :value="null" disabled
                >일요일에 할 루틴을 선택하세요</b-form-select-option
              >
            </template>
          </b-form-select>
        </b-form-group>
        <br />
        <div style="text-align:center">
        <b-button variant="outline-success" @click="createRoutine"
          >등록</b-button>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      title: null,
      maxNum: null,
      order: 0,
      routineTitles: [],
      routine1: null,
      routine2: null,
      routine3: null,
      routine4: null,
      routine5: null,
      routine6: null,
      routine7: null,
    };
  },
  methods: {
    createRoutine() {
      let info = {
        userId: sessionStorage.getItem("USER_ID"),
        groupName: this.title,
        maximumPeople: this.maxNum,
      };
      let challenges = [
        {
          days: 0,
          groupNo: 0,
          id: 0,
          routineNo: this.routine1,
        },
        {
          days: 1,
          groupNo: 0,
          id: 0,
          routineNo: this.routine2,
        },
        {
          days: 2,
          groupNo: 0,
          id: 0,
          routineNo: this.routine3,
        },
        {
          days: 3,
          groupNo: 0,
          id: 0,
          routineNo: this.routine4,
        },
        {
          days: 4,
          groupNo: 0,
          id: 0,
          routineNo: this.routine5,
        },
        {
          days: 5,
          groupNo: 0,
          id: 0,
          routineNo: this.routine6,
        },
        {
          days: 6,
          groupNo: 0,
          id: 0,
          routineNo: this.routine7,
        },
      ];
      let group = {
        groupInfo: info,
        rpdlist: challenges,
      };
      let JSONgroup = JSON.stringify(group);
      console.log(JSONgroup);
      this.$store.dispatch("createGroup", JSONgroup);
    },
  },
  computed: {
    ...mapState(["routines"]),
  },
  created() {
    this.$store.dispatch("getRoutines");
  },
  mounted() {
    for (let rou of this.routines) {
      let tmp = {
        value: rou.routineNo,
        text: rou.title,
      };
      this.routineTitles.push(tmp);
    }
  },
};
</script>

<style></style>
