import axios from "axios";
import Vue from "vue";
import Vuex from "vuex";
import router from "@/router";

Vue.use(Vuex);

const REST_API = `http://localhost:8236`;

export default new Vuex.Store({
  state: {
    meals: [],
    meal: {},
    routines: [],
    routine: {},
    exercises: [],
    exercise: {},
    isLogin: false,
    user_no: null,
    user_id: null,
    weight: null,
    height: null,
    gym_name: null,
    prupose: null,
    filepath: null,
    group_no: null,
    user_point: null,
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
    GET_EXERCISES(state, payload) {
      state.exercises = payload;
    },
    USER_LOGIN(state, payload) {
      state.isLogin = true;
      state.user_no = payload.user_no;
      state.user_id = payload.user_id;
      state.weight = payload.weight;
      state.height = payload.height;
      state.gym_name = payload.gym_name;
      state.prupose = payload.prupose;
      state.filepath = payload.filepath;
      state.group_no = payload.group_no;
      state.user_point = payload.user_point;
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
    getExercises({ commit }, payload) {
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
          commit("GET_EXERCISES", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    userLogin({ commit }, user) {
      const API_URL = `${REST_API}/login`;
      axios({
        url: API_URL,
        method: "POST",
        params: user,
      }).then((res) => {
        console.log(res);
        commit("USER_LOGIN");
        sessionStorage.setItem("access-token", res.data["access-token"]);
        router.push({ name: "main" });
      });
    },
  },
  modules: {},
});
