<template>
  <div>
    {{ member }}
    {{ user }}
    {{ Date(member[0].isDone) }}
    <hr />
    {{ isSameDate(new Date(member[0].isDone), week[0]) }}
    <!-- <svg width="400" height="100">
      <text x="20" y="50" text-anchor="middle">{{ week[0] }}</text>
      <rect
        x="20"
        y="60"
        rx="6"
        fill="#BFADF8"
        v-for="date in member"
        :key="date.id"
      ></rect>
    </svg> -->
    <div
      id="rect"
      width="30"
      height="30"
      style="border: 1px solid lightblue"
      background-color="#000000"
    ></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // weekBefore: null,
      week: [],
    };
  },
  props: {
    member: Array,
    user: String,
  },
  created() {
    for (let i = 6; i >= 0; i--) {
      let nowDate = new Date();
      let weekDate = nowDate.getTime() - i * 24 * 60 * 60 * 1000;
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
      this.week.push(resultDate);
    }
    console.log(this.week);
  },
  methods: {
    isSameDate(date1, date2) {
      return (
        date1.getMonth() === date2.getMonth() &&
        date1.getDate() === date2.getDate()
      );
    },
  },
};
</script>

<style scoped>
#rect {
  width: 30px;
  height: 30px;
  background-color: #bfadf8;
  border-radius: 20%;
}
text {
  font: bold 15px sans-serif;
  fill: #505050;
}
</style>
