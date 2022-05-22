<template>
  <div id="app">
    <header-nav></header-nav>
    <router-view />
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
