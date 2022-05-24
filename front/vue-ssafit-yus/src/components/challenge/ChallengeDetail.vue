<template>
  <div>
    {{ weekBefore }}
    test 중입니당
    <svg width="400" height="30">
      <rect
        v-for="(rect, index) in strict"
        :key="index"
        :x="rect.x"
        y="0"
        fill="#BFADF8"
        rx="6"
      ></rect>
    </svg>
  </div>
</template>

<script>
export default {
  data() {
    return {
      weekBefore: null,
      strict: [],
    };
  },
  created() {
    let nowDate = new Date();
    let weekDate = nowDate.getTime() - 7 * 24 * 60 * 60 * 1000;
    nowDate.setTime(weekDate);

    let weekYear = nowDate.getFullYear();
    let weekMonth = nowDate.getMonth() + 1;
    let weekDay = nowDate.getDate();

    if (weekMonth < 10) {
      weekMonth = "0" + weekMonth;
    }
    if (weekDay < 10) {
      weekDay = "0" + weekDay;
    }

    let resultDate = weekYear + "-" + weekMonth + "-" + weekDay;
    this.weekBefore = resultDate;
    console.log(resultDate);
    let xLoc = 0;
    for (let i = 0; i < 7; i++) {
      let tmp = {
        x: xLoc,
      };
      xLoc += 40;
      this.strict.push(tmp);
    }
  },
};
</script>

<style scoped>
rect {
  width: 30px;
  height: 30px;
  border-radius: 10%;
}
</style>
