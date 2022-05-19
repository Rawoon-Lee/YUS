<template>
  <header>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand :to="{ name: 'main' }">
        <img src="@/assets/logo.png" alt="" width="100px" height="" />
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto" tabs align="center">
          <b-nav-item to="/routine">루틴</b-nav-item>
          <b-nav-item :to="{ name: 'challenge' }">운동 챌린지</b-nav-item>
          <b-nav-item to="/meal">식단 게시판</b-nav-item>
          <b-nav-item to="/exercise">운동정보</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </header>
</template>

<script>
export default {
  name: "HeaderNav",
};
</script>

<style></style>
