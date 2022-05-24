<template>
  <div>
    <div v-if="commsYou">
      {{ videoId }}
      <comment-box
        v-for="(comm, index) in commsYou"
        :key="index"
        :comm="comm.comm"
        :userId="comm.userId"
        :regDate="comm.regDate"
      ></comment-box>
      {{ commsYou[0].comm }}
    </div>
  </div>
</template>

<script>
import CommentBox from "@/components/comments/CommentBox.vue";

export default {
  name: "CommentList",
  props: {
    videoId: String,
    commsYou: Array,
  },
  components: {
    CommentBox,
  },
};
</script>

<style></style>
