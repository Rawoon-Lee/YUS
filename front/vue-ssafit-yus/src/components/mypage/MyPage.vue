<template>
  <div>
    <div class="card" style="display: flex; align-items: center">
      <h2>마이페이지</h2>
      <div
        class="card-body"
        style="
          width: 28rem;
          display: flex;
          justify-content: space-around;
          align-items: center;
        "
      >
        <img
          :src="require(`@/assets/UserInfo/${userId}.png`)"
          alt="프로필"
          class="mypageProfile"
        />
        <div>
          <p class="card-text">닉네임 : {{ userId }}</p>
          <p class="card-text">
            몸무게 / 키 : {{ userInfo.weight }} / {{ userInfo.height }}
          </p>
          <p class="card-text" v-show="userInfo.groupNo > 0">
            챌린지 그룹 : {{ group.groupName }}
          </p>
        </div>
      </div>
      <div
        style="
          width: 22rem;
          display: flex;
          justify-content: space-around;
          align-items: center;
        "
      >
        <b-button :to="{ name: 'myInfoUpdateProfile' }"
          >프로필 사진 수정하기</b-button
        >
        <b-button :to="{ name: 'myInfoUpdate' }">개인정보 수정하기</b-button>
      </div>
      <br />
    </div>
    <challenge-detail></challenge-detail>
  </div>
</template>

<script>
import { mapState } from "vuex";
import ChallengeDetail from "@/components/challenge/ChallengeDetail.vue";

export default {
  data() {
    return {
      userId: sessionStorage.getItem("USER_ID"),
    };
  },
  computed: {
    ...mapState(["userInfo"]),
    ...mapState(["groupMem"]),
    ...mapState(["group"]),
    ...mapState(["keys"]),
  },
  created() {
    this.userId = sessionStorage.getItem("USER_ID");
    console.log(this.userId);
    this.$store.dispatch("getUserInfo", this.userId);
    this.$store.dispatch("getGroup", this.userInfo.groupNo);
  },
  components: {
    ChallengeDetail,
  },
};
</script>

<style>
.mypageProfile {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  object-fit: cover;
}
</style>
