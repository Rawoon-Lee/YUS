<template>
  <div class="challenge">
    <div
      style="
        height: 200px;
        background-color: rgba(0, 0, 0, 0.2);
        display: flex;
        margin: auto;
      "
    >
      <h1
        style="
          text-align: center;
          font-size: 60pt;
          font-style: italic;
          margin: auto;
          font-weight: bold;
        "
      >
        CHALLENGE
      </h1>
    </div>
    <div style="height: 65%">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
