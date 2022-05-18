import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import MainView from "../views/MainView.vue";
import RoutineView from "../views/RoutineView.vue";
import ChallengeView from "../views/ChallengeView.vue";
import BoardView from "@/views/BoardView.vue";
import ExerciseView from "@/views/ExerciseView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/main",
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
    path: "/board",
    name: "board",
    component: BoardView,
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
