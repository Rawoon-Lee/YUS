<template>
  <div class="container">
    <br>
    <v-btn 
    class="pink white--text"
    :to="{ name: 'exerciseListAll' }"
    style="margin-right:0.5rem"
    >전체
    </v-btn>
    <v-btn 
    color="grey darken-4"
    :to="{ name: 'exerciseListAll' }"
    style="margin-right:0.5rem"
    >순위
    </v-btn>
    <v-btn 
    color="grey darken-3"
    :to="{ name: 'exerciseListShoulder' }"
    style="margin-right:0.5rem"
    >어깨
    </v-btn>
    <v-btn 
    color="grey darken-3"
    :to="{ name: 'exerciseListChest' }"
    style="margin-right:0.5rem"
    >가슴
    </v-btn>
    <v-btn 
    color="grey darken-3"
    :to="{ name: 'exerciseListBack' }"
    style="margin-right:0.5rem"
    >등
    </v-btn>
    <v-btn 
    color="grey darken-3"
    :to="{ name: 'exerciseListLegs' }"
    >하체
    </v-btn>
    <hr>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "ExerciseList",
  data() {
    return {
      keyword: "",
      mode: 1,
      currentPage: 1,
      perPage: 10,
    };
  },
  created() {
    this.$store.dispatch("getVideos");
    // this.$store.dispatch("getExercises");
  },
};
</script>

<style></style>
