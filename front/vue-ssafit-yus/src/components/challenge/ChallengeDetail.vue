<template>
  <div>
    <br />
    <h2 style="text-align: center">{{ group.groupName }}</h2>
    <hr />
    <div class="m-4">
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
          <div class="col" style="width: 50%; margin-left: 3rem">
            <br /><br />
            <h3 style="text-align: center">그룹 정보</h3>
            <hr />
            <table class="table" style="text-align: center">
              <tr>
                <td>그룹명 : {{ group.groupName }}</td>
              </tr>
              <tr>
                <td>리더 : {{ group.userId }}</td>
              </tr>
              <tr>
                <td>
                  인원 : {{ group.currentPeople }} / {{ group.maximumPeople }}
                </td>
              </tr>
              <tr>
                <td>Activity Credit : {{ group.groupPoint }}</td>
              </tr>
              <hr />
              <tr>
                <h4>최근 일주일간의 운동 기록</h4>
              </tr>
              <div
                class="card"
                style="
                  width: 25rem;
                  display: flex;
                  align-items: center;
                  margin-left: 200px;
                "
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
              <b-button
                v-if="userInfo.groupNo == 0"
                style="margin-top: 10px"
                @click="enroll(group.groupNo)"
                >가입 신청</b-button
              >
              <button
                v-if="userInfo.groupNo == group.groupNo"
                style="margin-top: 10px"
                type="button"
                class="btn btn-secondary"
                v-show="keys.includes(userId)"
                @click="success"
              >
                운동 달성!
              </button>
            </table>
          </div>
          <div class="col" style="width: 10%">
            <br /><br />
            <h3 style="text-align: center">WEEKLY ROUTINE</h3>
            <hr />
            <div class="d-flex justify-content-center flex-wrap .container-md">
              <div v-for="(item, index) in rpdlist" :key="index">
                <div v-if="index == today - 1">
                  <div
                    class="card h-200"
                    style="
                      width: 380px;
                      margin-right: 2rem;
                      background-color: #bfadf8;
                    "
                  >
                    <div class="card-body">
                      <h5 class="videotitle">day {{ index + 1 }}</h5>
                      <div style="text-align: center; font-weight: bold">
                        <b-link
                          style="color: #646464"
                          :to="`/routine/${item[0].routineNo}`"
                          ><b-icon icon="link45deg" style="color: blue"></b-icon
                          >{{ item[0].title }}</b-link
                        >
                      </div>
                      <div class="videotext" style="text-align: right">
                        {{ item[0].userId }}
                      </div>
                    </div>
                  </div>
                </div>
                <div v-else>
                  <div
                    class="card h-200"
                    style="
                      width: 380px;
                      margin-right: 2rem;
                      background-color: #aaaaaa;
                    "
                  >
                    <div class="card-body">
                      <h5 class="videotitle">day {{ index + 1 }}</h5>
                      <div style="text-align: center; font-weight: bold">
                        <b-link
                          style="color: black"
                          :to="`/routine/${item[0].routineNo}`"
                          ><b-icon icon="link45deg" style="color: blue"></b-icon
                          >{{ item[0].title }}</b-link
                        >
                      </div>
                      <div class="videotext" style="text-align: right">
                        {{ item[0].userId }}
                      </div>
                    </div>
                  </div>
                </div>
                <div
                  class="d-flex justify-content-start flex-wrap"
                  style="margin: 3%"
                ></div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="d-flex justify-content-center" style="margin-top: 10px">
        <b-button to="/challenge">목록으로 돌아가기</b-button>
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
      groupNo: 0,
      today: 0,
    };
  },
  computed: {
    ...mapState(["groupMem"]),
    ...mapState(["group"]),
    ...mapState(["keys"]),
    ...mapState(["rpdlist"]),
    ...mapState(["userInfo"]),
  },
  components: {
    ChallengeDetailUser,
  },
  created() {
    let now = new Date();
    this.today = now.getDay();
    console.log(this.today);
    let userId = sessionStorage.getItem("USER_ID");
    let pathname = this.$route.path.split("/");
    let id = pathname[pathname.length - 1];
    this.$store.dispatch("getUserInfo", userId);
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
    enroll() {
      let user = {
        userId: this.userId,
        groupNo: this.groupNo,
      };
      console.log(JSON.stringify(user));
      this.$store.dispatch("enrollGroup", JSON.stringify(user));
      this.$router.go();
    },
  },
};
</script>
