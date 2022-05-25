<template>
  <div>
    <div>
      <div style="width: 80px; float: left; margin-left: 20px">{{ user }}</div>
      <div
        v-for="(date, index) in week"
        :key="index"
        :class="[isSameDate(date) ? 'colored' : 'uncolored']"
        width="30"
        height="30"
        style="border: 0px; float: left; margin-right: 8px"
        background-color="#000000"
      ></div>
    </div>
    <br />
    <br />
  </div>
</template>

<script>
export default {
  data() {
    return {
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
    console.log(typeof this.member[0]);
  },
  methods: {
    isSameDate(date1) {
      let flag = false;
      for (let date2 of this.member) {
        // console.log(JSON.stringify(date2));
        // console.log(date2["isDone"]);
        if (
          new Date(date1).getMonth() === new Date(date2["isDone"]).getMonth() &&
          new Date(date1).getDate() === new Date(date2["isDone"]).getDate()
        ) {
          flag = true;
          break;
        }
      }
      return flag;
    },
  },
};
</script>

<style scoped>
.uncolored {
  width: 30px;
  height: 30px;
  background-color: #aaaaaa;
  border-radius: 25%;
}
.colored {
  width: 30px;
  height: 30px;
  background-color: #bfadf8;
  border-radius: 25%;
}
text {
  font: bold 15px sans-serif;
  fill: #505050;
}
</style>
