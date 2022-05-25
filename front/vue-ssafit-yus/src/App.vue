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

@font-face {
    font-family: 'EliceDigitalBaeum_Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_220508@1.0/EliceDigitalBaeum_Regular.woff2') format('woff2');
    font-weight: 400;
    font-style: normal;
}

#app {
  font-family: "EliceDigitalBaeum_Regular";
}
</style>