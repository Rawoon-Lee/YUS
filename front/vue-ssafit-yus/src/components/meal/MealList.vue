<template>
  <div class="container">
    <h2 style="margin-top: 2rem">식단 목록</h2>
    <hr />
    <div style="text-align: right; margin-bottom: 1rem">
      <b-button :to="{ name: 'mealCreate' }">식단 등록</b-button>
    </div>
    <div class="d-flex justify-content-center flex-wrap .container-lg">
      <!-- 카드 도전 -->
      <div v-for="(meal, index) in pageMealList" :key="index">
        <v-card
          class="card h-150"
          hover
          style="
            min-height: 380px;
            width: 300px;
            margin-left: 2rem;
            margin-right: 2rem;
            margin-bottom: 2rem;
          "
        >
          <v-img
            :src="require(`@/assets/MealBoard/${meal.filepath}.png`)"
            height="200px"
          ></v-img>

          <v-card-title>{{ meal.title }}</v-card-title>

          <v-card-subtitle>
            <div>
              {{ meal.userId }}
            </div>
            <div>
              {{ meal.regDate }}
            </div>
          </v-card-subtitle>

          <v-card-text style="text-align: right; margin-left: 5px">
            <v-icon color="#CD1039" style="margin-left: 5px">mdi-heart</v-icon>
            <span>
              {{ meal.LikedCnt }}
            </span>
            <b-icon-eye-fill style="margin-left: 8px"></b-icon-eye-fill>
            <span>
              {{ meal.viewCnt }}
            </span>
            <span style="margin-left: 8px"
              >{{ meal.carb * 3 + meal.protein * 2 + meal.fat * 4 }} kcal</span
            >
          </v-card-text>
        </v-card>
      </div>
    </div>
    <div>
      <b-form-select
        v-model="mode"
        style="
          width: 100px;
          height: 40px;
          margin-bottom: 0.5rem;
          margin-right: 0.5rem;
        "
      >
        <option value="1">제목 :</option>
        <option value="2">내용 :</option>
        <option value="3">제목+내용 :</option>
      </b-form-select>
      <input
        type="text"
        v-model="keyword"
        style="
          border: 1px solid;
          border-radius: 4px;
          margin-top: 0.5rem;
          height: 40px;
        "
      />
      <button
        @click="search"
        style="
          margin-left: 1rem;
          margin-bottom: 1rem;
          color: gray;
          border: 1px solid;
          width: 60px;
          border-radius: 5px;
          height: 40px;
        "
      >
        검색
      </button>
    </div>

    <b-pagination
      page-class="customPage"
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
    ></b-pagination>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "MealList",
  data() {
    return {
      keyword: "",
      mode: 1,
      currentPage: 1,
      perPage: 10,
    };
  },
  computed: {
    ...mapState(["meals"]),
    rows() {
      return this.meals.length;
    },
    pageMealList() {
      return this.meals.slice(
        (this.currentPage - 1) * this.perPage,
        this.currentPage * this.perPage
      );
    },
  },
  created() {
    this.$store.dispatch("getMeals");
  },
  methods: {
    search() {
      const payload = {
        mode: this.mode,
        keyword: this.keyword,
      };
      this.$store.dispatch("getMeals", payload);
    },
  },
};
</script>

<style></style>
