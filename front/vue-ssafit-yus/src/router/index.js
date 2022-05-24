import Vue from "vue";
import VueRouter from "vue-router";
// import HomeView from "../views/HomeView.vue";
import MainView from "../views/MainView.vue";

import RoutineView from "@/views/RoutineView.vue";
import RoutineList from "@/components/routine/RoutineList.vue";
import RoutineCreateHealth from "@/components/routine/RoutineCreateHealth.vue";
import RoutineDetail from "@/components/routine/RoutineDetail.vue";

import ChallengeView from "../views/ChallengeView.vue";
import GroupList from "@/components/challenge/ChallengeGroupList.vue";
import GroupCreate from "@/components/challenge/ChallengeGroupCreate.vue";

import MealView from "@/views/MealView.vue";
import MealCreate from "@/components/meal/MealCreate.vue";
import MealDetail from "@/components/meal/MealDetail.vue";
import MealList from "@/components/meal/MealList.vue";
import MealUpdate from "@/components/meal/MealUpdate.vue";

import ExerciseView from "@/views/ExerciseView.vue";
import ExerciseCreate from "@/components/exercise/ExerciseCreate.vue";
import ExerciseDetail from "@/components/exercise/ExerciseDetail.vue";

import ExerciseList from "@/components/exercise/ExerciseList.vue";
import ExerciseListAll from "@/components/exercise/ExerciseListAll.vue";
import ExerciseListShoulder from "@/components/exercise/ExerciseListShoulder.vue";
import ExerciseListChest from "@/components/exercise/ExerciseListChest.vue";
import ExerciseListBack from "@/components/exercise/ExerciseListBack.vue";
import ExerciseListLegs from "@/components/exercise/ExerciseListLegs.vue";

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
    component: RoutineView,
    children: [
      {
        path: "",
        name: "routineList",
        component: RoutineList,
      },
      {
        path: "create/health",
        name: "routineCreateHealth",
        component: RoutineCreateHealth,
      },
      {
        path: ":id",
        name: "routineDetail",
        component: RoutineDetail,
      },
    ],
  },
  {
    path: "/exercise",
    component: ExerciseView,
    children: [
      {
        path: "",
        name: "exerciseList",
        component: ExerciseList,
        children: [
          {
            path: "all",
            name: "exerciseListAll",
            component: ExerciseListAll,
          },
          {
            path: "shoulder",
            name: "exerciseListShoulder",
            component: ExerciseListShoulder,
          },
          {
            path: "chest",
            name: "exerciseListChest",
            component: ExerciseListChest,
          },
          {
            path: "back",
            name: "exerciseListBack",
            component: ExerciseListBack,
          },
          {
            path: "legs",
            name: "exerciseListLegs",
            component: ExerciseListLegs,
          },
        ],
      },
      {
        path: "create",
        name: "exerciseCreate",
        component: ExerciseCreate,
      },
      {
        path: "detail/:id",
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
      {
        path: "create",
        name: "groupCreate",
        component: GroupCreate,
      },
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

router.beforeEach((to, from, next) => {
  if (to.path == "/") {
    next();
  } else if (
    !sessionStorage.getItem("access-token") &&
    !to.path.includes("member")
  ) {
    alert("로그인을 완료해야 이용 가능한 기능입니다.");
    next("/member/login");
  } else {
    next();
  }
});

export default router;
