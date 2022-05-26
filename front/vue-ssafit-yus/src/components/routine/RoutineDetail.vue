<template>
  <div class="container">
    <br />
    <h2 style="text-align: center">{{ routine.title }}</h2>
    <hr />
    <div align="right">
      <span style="margin-right: 1rem"
        ><b-icon-eye-fill></b-icon-eye-fill> {{ routine.viewCnt }}</span
      >
      <span style="margin-right: 1rem"
        ><b-icon icon="heart-fill" style="color: red"></b-icon>
        {{ routine.likedCnt }}</span
      >
      <span
        ><b-icon icon="chat-right-text-fill"></b-icon>
        {{ commsRoutine.length }}</span
      >
    </div>
    <div class="m-4" v-if="routine">
      <div
        style="
          width: 90%;
          height: 700px;
          margin: auto;
          border: 1px solid gray;
          border-radius: 10px;
        "
      >
        <div
          style="
            display: flex;
            justify-content: space-between;
            text-align: center;
          "
        >
          <div align="left" style="width: 50%; margin-left: 3rem">
            <br /><br />
            <h3 style="text-align: center">내용</h3>
            <hr />
            <table class="table" style="text-align: center">
              <tr>
                <td>제목 : {{ routine.title }}</td>
              </tr>
              <tr>
                <td>{{ routine.userId }} / {{ routine.regDate }}</td>
              </tr>
              <tr>
                <td>
                  조회수 : {{ routine.viewCnt }} | 좋아요 :
                  {{ routine.likedCnt }}
                </td>
              </tr>
              <tr>
                <td>내용 : {{ routine.content }}</td>
              </tr>
            </table>
          </div>
          <div align="right" style="width: 40%; margin: auto">
            <br /><br />
            <h3 style="text-align: center">루틴</h3>
            <hr />
            <div style="margin: auto">
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
    <div class="row justify-content-center">
      <b-button
        v-show="!isRouLiked"
        type="button"
        @click="addLike"
        variant="light"
      >
        <b-icon icon="heart-fill" style="color: red"></b-icon> 좋아요
      </b-button>
      <b-button
        v-show="isRouLiked"
        type="button"
        @click="delLike"
        variant="light"
      >
        <b-icon icon="heart"></b-icon> 취소
      </b-button>
    </div>
    <div v-if="commsRoutine.length" style="margin-left: 2rem">
      <div v-for="(comm, index) in commsRoutine" :key="index">
        <div class="row" v-if="comm.classNo == 0" style="margin-top: 1rem">
          <div class="col">{{ comm.comm }}</div>
          <div class="col">{{ comm.userId }}</div>
          <div class="col">{{ comm.regDate }}</div>
          <div class="col">
            <b-button type="button" @click="toggleShow(index)" variant="light">
              <b-icon icon="reply-fill"></b-icon>답글
            </b-button>
            <b-button
              v-if="comm.userId == userId"
              type="button"
              @click="deleteComm(comm.commIndex)"
              variant="danger"
            >
              <b-icon icon="trash-fill"></b-icon>삭제
            </b-button>
          </div>
        </div>
        <div class="row" style="margin-left: 12px; margin-top: 0.5rem" v-else>
          <div><b-icon icon="arrow-return-right"></b-icon></div>
          <div class="col">{{ comm.comm }}</div>
          <div class="col">{{ comm.userId }}</div>
          <div class="col">{{ comm.regDate }}</div>
          <div class="col">
            <b-button type="button" @click="toggleShow(index)" variant="light">
              <b-icon icon="reply-fill"></b-icon>답글
            </b-button>
            <b-button
              v-if="comm.userId == userId"
              type="button"
              @click="deleteComm(comm.commIndex)"
              variant="danger"
            >
              <b-icon icon="trash-fill"></b-icon>삭제
            </b-button>
          </div>
        </div>
        <div v-if="comm.status">
          <routine-Comment-form
            :classNo="1"
            :commGroup="comm.commGroup"
            :userId="userId"
            :routineNo="routine.routineNo"
          ></routine-Comment-form>
        </div>
      </div>
    </div>
    <div v-else>
      <h2
        style="
          margin-left: 1rem;
          color: gray;
          font-size: 20pt;
          text-align: center;
        "
      >
        작성된 댓글이 없습니다.
      </h2>
    </div>
    <hr />
    <div>
      <b-icon icon="chat-left-text-fill" style="margin-top: 1rem"></b-icon> 댓글
      작성({{ commsRoutine.length }})
    </div>
    <br />
    {{ userId }}
    <routine-comment-form
      :classNo="0"
      :commGroup="0"
      :userId="userId"
      :routineNo="routine.routineNo"
    ></routine-comment-form>
    <hr />
    <div
      class="row justify-content-center"
      style="margin-left: 2px; margin-right: 5px"
    >
      <b-button to="/routine">목록으로 돌아가기</b-button>
    </div>
    <hr />
    <br />
  </div>
</template>

<script>
import { mapState } from "vuex";
import RoutineDetailExercise from "@/components/routine/RoutineDetailExercise.vue";
import RoutineCommentForm from "@/components/comments/RoutineCommentForm.vue";

export default {
  name: "RoutineDetail",
  data() {
    return {
      userId: sessionStorage.getItem("USER_ID"),
      routineId: null,
    };
  },
  components: {
    RoutineDetailExercise,
    RoutineCommentForm,
  },
  computed: {
    ...mapState(["routine"]),
    ...mapState(["workouts"]),
    ...mapState(["isRouLiked"]),
    ...mapState(["commsRoutine"]),
    ...mapState(["commsYou"]),
  },
  created() {
    const pathName = this.$route.path.split("/");
    const id = pathName[pathName.length - 1];
    this.$store.dispatch("getRoutine", id);
    this.$store.dispatch("getCommentsRoutine", id);
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
    toggleShow(index) {
      this.commsRoutine[index].status = !this.commsRoutine[index].status;
    },
    deleteComm(commIndex) {
      let routineComm = {
        commIndex: commIndex,
        routineNo: this.routine.routineNo,
      };
      this.$store.dispatch("delRoutineComm", JSON.stringify(routineComm));
    },
  },
};
</script>

<style scoped></style>
