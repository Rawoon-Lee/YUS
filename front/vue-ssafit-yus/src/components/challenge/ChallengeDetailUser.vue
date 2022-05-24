<template>
  <div>
    <svg width="400" height="30">
      <rect x="10" y="0" rx="6" fill="#BFADF8" id=""></rect>
    </svg>
    {{ member }}
  </div>
</template>

<script>
export default {
  data() {
    return {
      weekBefore: null,
    };
  },
  props: {
    member: Array,
    user: String,
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
