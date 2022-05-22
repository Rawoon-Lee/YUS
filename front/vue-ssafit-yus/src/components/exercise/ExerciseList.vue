<template>
  <div class="container">
    <hr />
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-nav class="ml">
        <b-nav-item :to="{ name: 'exerciseListAll' }">전체</b-nav-item>
        <b-nav-item :to="{ name: 'exerciseListAll' }">순위</b-nav-item>
        <b-nav-item :to="{ name: 'exerciseListShoulder' }">어깨</b-nav-item>
        <b-nav-item :to="{ name: 'exerciseListChest' }">가슴</b-nav-item>
        <b-nav-item :to="{ name: 'exerciseListBack' }">등</b-nav-item>
        <b-nav-item :to="{ name: 'exerciseListLegs' }">하체</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
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
