import axios from "axios";
import Vue from "vue";
import Vuex from "vuex";
import router from "@/router";

Vue.use(Vuex);

const REST_API = `http://localhost:8236`;

export default new Vuex.Store({
  state: {
    groups: [],
    group: {},
    meals: [],
    meal: {},
    routines: [],
    routine: {},
    exercises: [],
    exercise: {},
    videos: [],
    video: {},
    isJoin: 0,
    isLogin: false,
    user_no: null,
  },
  getters: {},
  mutations: {
    GET_MEALS(state, payload) {
      state.meals = payload;
    },
    GET_MEAL(state, payload) {
      state.meal = payload;
    },
    CREATE_MEAL(state, payload) {
      state.meals.push(payload);
    },
    GET_VIDEOS(state, payload) {
      state.videos = payload;
    },
    GET_VIDEO(state, payload) {
      state.video = payload;
    },
    GET_EXERCISES(state, payload) {
      state.exercises = payload;
    },
    GET_GROUPS(state, payload) {
      state.groups = payload;
    },
    GET_ROUTINES(state, payload) {
      state.routines = payload;
    },
    USER_LOGIN(state, payload) {
      state.isLogin = true;
      state.user_no = payload;
    },
    CREATE_USER(state, payload) {
      state.isJoin = payload;
    },
  },
  actions: {
    getMeals({ commit }, payload) {
      let params = null;
      if (payload) {
        params = payload;
      }
      const API_URL = `${REST_API}/meal/info`;
      axios({
        url: API_URL,
        method: "GET",
        params,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          commit("GET_MEALS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    createMeal({ commit }, meal) {
      const API_URL = `${REST_API}/meal`;
      axios({
        url: API_URL,
        method: "POST",
        params: meal,
      })
        .then(() => {
          commit("CREATE_MEAL", meal);
          router.push("/meal");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateMeal({ commit }, meal) {
      const API_URL = `${REST_API}/meal`;
      axios({
        url: API_URL,
        method: "PUT",
        params: meal,
      })
        .then(() => {
          commit("UPDATE_MEAL", meal);
          router.push({ name: "mealDetail", params: { id: meal.post_no } });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getVideos({ commit }, payload) {
      let params = null;
      if (payload) {
        params = payload;
      }
      const API_URL = `${REST_API}/youtube/info`;
      axios({
        url: API_URL,
        method: "GET",
        params,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          console.log(res);
          commit("GET_VIDEOS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getVideo({ commit }, id) {
      for (let video of this.$store.state.videos) {
        console.log(video);
        if (video.videoId == id) {
          commit("GET_VIDEO", video);
          console.log(video);
          break;
        }
      }
    },
    getExercises({ commit }, payload) {
      let params = null;
      if (payload) {
        params = payload;
      }
      const API_URL = `${REST_API}/exercise/info`;
      axios({
        url: API_URL,
        method: "GET",
        params,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          console.log(res);
          commit("GET_EXERCISES", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getGroups({ commit }, payload) {
      let params = null;
      if (payload) {
        params = payload;
      }
      const API_URL = `${REST_API}/group/info`;
      axios({
        url: API_URL,
        params,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          console.log(res.data);
          commit("GET_GROUPS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getRoutines({ commit }, payload) {
      let params = null;
      if (payload) {
        params = payload;
      }
      const API_URL = `${REST_API}/routine/info`;
      axios({
        url: API_URL,
        method: "GET",
        params,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          console.log(res);
          commit("GET_ROUTINES", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    userLogin({ commit }, user) {
      const API_URL = `${REST_API}/user/login`;
      axios({
        url: API_URL,
        method: "POST",
        params: user,
      })
        .then((res) => {
          console.log(res);
          commit("USER_LOGIN");
          sessionStorage.setItem("access-token", res.data["access-token"]);
          router.push({ name: "main" });
        })
        .catch((err) => {
          console.log(err);
          console.log(user);
        });
    },
    createUser({ commit }, user) {
      const API_URL = `${REST_API}/user/register`;
      axios({
        url: API_URL,
        method: "POST",
        params: user,
      })
        .then((res) => {
          console.log(res);
          commit("CREATE_USER", 1);
        })
        .catch((err) => {
          console.log(err.toJSON().status);
          if (err.toJSON().status == 409) {
            commit("CREATE_USER", 2);
          } else {
            commit("CREATE_USER", 0);
          }
        });
    },
  },
  modules: {},
});
