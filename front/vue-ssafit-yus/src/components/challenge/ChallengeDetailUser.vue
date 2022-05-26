<template>
  <div
    style="
      display: flex;
      align-items: center;

      padding-bottom: 0px;
    "
  >
    <div style="display: flex; justify-content: space-between; width: 100%">
      <div>
        <img
          :src="require(`@/assets/UserInfo/${user}.png`)"
          alt="프로필"
          id="profile"
          style="float: left; margin-right: 5px"
        />
        <div style="float: left; margin-right: 30px">{{ user }}</div>
      </div>
      <div style="display: flex; justify-content: center">
        <div
          v-for="(date, index) in week"
          :key="index"
          :class="[isSameDate(date) ? 'colored' : 'uncolored']"
          style="border: 0px; margin: 3px"
          background-color="#000000"
        ></div>
      </div>
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
  width: 25px;
  height: 25px;
  background-color: #aaaaaa;
  border-radius: 25%;
}
.colored {
  width: 25px;
  height: 25px;
  background-color: #bfadf8;
  border-radius: 25%;
}
text {
  font: bold 15px sans-serif;
  fill: #505050;
}
#profile {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  object-fit: cover;
}
</style>
