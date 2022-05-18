import Vue from "vue";
import VueRouter from "vue-router";
// import HomeView from "../views/HomeView.vue";
import MainView from "../views/MainView.vue";

import RoutineView from "@/views/RoutineView.vue";

import ChallengeView from "../views/ChallengeView.vue";

import MealView from "@/views/MealView.vue";
import MealCreate from "@/components/meal/MealCreate.vue";
import MealDetail from "@/components/meal/MealDetail.vue";
import MealList from "@/components/meal/MealDetail.vue";
import MealUpdate from "@/components/meal/MealUpdate.vue";

import ExerciseView from "@/views/ExerciseView.vue";

import MemberLogin from "@/components/member/MemberLogin.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  {
    path: "/routine",
    name: "routine",
    component: RoutineView,
  },
  {
    path: "/exercise",
    name: "exercise",
    component: ExerciseView,
  },
  {
    path: "/member",
    component: MemberLogin,
  },
  {
    path: "/meal",
    component: MealView,
    children: [
      {
        path: "",
        name: "mealList",
        component: MealList,
      },
      {
        path: "create",
        name: "mealCreate",
        component: MealCreate,
      },
      {
        path: "update",
        name: "mealUpdate",
        component: MealUpdate,
      },
      {
        path: ":id",
        name: "mealDetail",
        component: MealDetail,
      },
    ],
  },
  {
    path: "/challenge",
    name: "challenge",
    component: ChallengeView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
