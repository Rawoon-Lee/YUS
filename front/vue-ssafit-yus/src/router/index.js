import Vue from "vue";
import VueRouter from "vue-router";
// import HomeView from "../views/HomeView.vue";
import MainView from "../views/MainView.vue";

import RoutineView from "@/views/RoutineView.vue";

import ChallengeView from "../views/ChallengeView.vue";
import GroupList from "@/components/challenge/ChallengeGroupList.vue";

import MealView from "@/views/MealView.vue";
import MealCreate from "@/components/meal/MealCreate.vue";
import MealDetail from "@/components/meal/MealDetail.vue";
import MealList from "@/components/meal/MealList.vue";
import MealUpdate from "@/components/meal/MealUpdate.vue";

import ExerciseView from "@/views/ExerciseView.vue";
import ExerciseCreate from "@/components/exercise/ExerciseCreate.vue";
import ExerciseDetail from "@/components/exercise/ExerciseDetail.vue";
import ExerciseList from "@/components/exercise/ExerciseList.vue";
import ExerciseUpdate from "@/components/exercise/ExerciseUpdate.vue";

import MemberView from "@/views/MemberView.vue";
import MemberLogin from "@/components/member/MemberLogin.vue";
import MemberJoin from "@/components/member/MemberJoin.vue";
import MemberMyPage from "@/components/member/MemberMyPage.vue";

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
    component: ExerciseView,
    children: [
      {
        path: "",
        name: "exerciseList",
        component: ExerciseList,
      },
      {
        path: "create",
        name: "exerciseCreate",
        component: ExerciseCreate,
      },
      {
        path: "update",
        name: "exerciseUpdate",
        component: ExerciseUpdate,
      },
      {
        path: "detail:id",
        name: "exerciseDetail",
        component: ExerciseDetail,
      },
    ],
  },
  {
    path: "/member",
    component: MemberView,
    children: [
      {
        path: "login",
        name: "login",
        component: MemberLogin,
      },
      {
        path: "join",
        name: "join",
        component: MemberJoin,
      },
      {
        path: "mypage",
        name: "mypage",
        component: MemberMyPage,
      },
    ],
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
        path: "detail:id",
        name: "mealDetail",
        component: MealDetail,
      },
    ],
  },
  {
    path: "/challenge",
    component: ChallengeView,
    children: [
      {
        path: "",
        name: "groupList",
        component: GroupList,
      },
      // {
      //   path: "create",
      //   name: "mealCreate",
      //   component: MealCreate,
      // },
      // {
      //   path: "update",
      //   name: "mealUpdate",
      //   component: MealUpdate,
      // },
      // {
      //   path: "detail:id",
      //   name: "mealDetail",
      //   component: MealDetail,
      // },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
