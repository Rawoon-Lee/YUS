<template>
  <div id="app">
    <header-nav></header-nav>
    <router-view/>
  </div>
</template>

<script>
import HeaderNav from "@/components/common/HeadaerNav.vue";
export default {
  components: {
    HeaderNav,
  },
};
</script>
<style>
.customPage.page-item.active .page-link {
  background-color: #646464;
  border-color: black;
}
</style>