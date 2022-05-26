<template>
  <div class="container" style="margin-top: 1rem">
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
          :src="require(`@/assets/UserInfo/${userInfo.filepath}.png`)"
          alt="프로필"
          class="mypageProfile"
        />
        <div>
          <p class="card-text">닉네임 : {{ userInfo.userId }}</p>
          <p class="card-text">나이 : {{ userInfo.age }}</p>
          <p class="card-text">키 : {{ userInfo.height }}cm</p>
          <p class="card-text">몸무게 : {{ userInfo.weight }}kg</p> 
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
      <div
        v-if="userInfo.groupNo > 0"
        style="border: 0.5px solid black; border-radius: 10%; margin-top: 10px"
      >
        <b-link
          :to="`/challenge/detail/${userInfo.groupNo}`"
          style="color: #646464"
        >
          <b-icon icon="lightning-fill"></b-icon>
          work out
        </b-link>
      </div>
      <div v-else>
        <h2>진행중인 챌린지가 없습니다.</h2>
      </div>
      <br />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(["userInfo"]),
    ...mapState(["keys"]),
    ...mapState(["profilePath"]),
    ...mapState(["group"]),
  },
  created() {
    let userId = sessionStorage.getItem("USER_ID");
    this.$store.dispatch("getUserInfo", userId);
    if (this.userInfo.groupNo != 0)
      this.$store.dispatch("getGroup", this.userInfo.groupNo);
  },
  components: {},
};
</script>

<style>
.mypageProfile {
  width: 200px;
  height: 200px;
  border-radius: 12%;
  object-fit: cover;
}
</style>
