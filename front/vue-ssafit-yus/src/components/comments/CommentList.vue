<template>
  <div>
    <div v-if="commsYou">
      <comment-box
        v-for="(comm, index) in commsYou"
        :key="index"
        :comm="comm.comm"
        :userId="comm.userId"
        :regDate="comm.regDate"
        :classNo="comm.classNo"
      ></comment-box>
      {{ commsYou[0].comm }}
    </div>
    <div v-else>
      등록된 댓글이 없습니다.
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
