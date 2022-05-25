<template>
  <div>
    {{ group.groupName }}
    {{ group.groupPoint }}
    {{ group.maximumPeople }}
    {{ group.currentPeople }}
    {{ group.userId }}
    <h2>최근 일주일간의 운동 기록</h2>
    <challenge-detail-user
      v-for="(member, index) in keys"
      :key="index"
      :member="groupMem[member]"
      :user="member"
    ></challenge-detail-user>
  </div>
</template>

<script>
import { mapState } from "vuex";
import ChallengeDetailUser from "@/components/challenge/ChallengeDetailUser.vue";

export default {
  computed: {
    ...mapState(["groupMem"]),
    ...mapState(["group"]),
    ...mapState(["keys"]),
  },
  components: {
    ChallengeDetailUser,
  },
  created() {
    let pathname = this.$route.path.split("/");
    let id = pathname[pathname.length - 1];
    this.$store.dispatch("getGroup", id);
  },
};
</script>
