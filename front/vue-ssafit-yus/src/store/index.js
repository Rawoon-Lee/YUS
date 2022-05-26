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
    groupMem: [],
    keys: [],
    meals: [],
    meal: {},
    routines: [],
    routine: {},
    workouts: [],
    exercises: [],
    exercise: {},
    videos: [],
    video: {},
    VIDEOS: [],
    commsYou: [],
    isJoin: 0,
    isLogin: false,
    isYouLiked: false,
    isRouLiked: false,
    isCreatedRou: false,
    USER_ID: null,
    userInfo: null,
    profilePath: "profile-default",
  },
  getters: {
    getGroupMem(state) {
      return state.groupMem;
    },
  },
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
    GET_GROUP(state, payload) {
      state.group = payload;
    },
    GET_MEM(state, payload) {
      state.groupMem = payload;
    },
    GET_KEYS(state, payload) {
      state.keys = payload;
    },
    GET_ROUTINES(state, payload) {
      state.routines = payload;
    },
    GET_ROUTINE(state, payload) {
      state.routine = payload;
    },
    GET_WORKOUTS(state, payload) {
      state.workouts = payload;
    },
    CREATE_ROUTINE(state, payload) {
      state.isRouCreated = payload;
      console.log(state.isRouCreated);
    },
    USER_LOGIN(state, payload) {
      state.USER_ID = payload;
    },
    USER_ISLOGIN(state, payload) {
      state.isLogin = payload;
    },
    CREATE_USER(state, payload) {
      state.isJoin = payload;
    },
    LOGOUT(state) {
      state.isLogin = false;
    },
    CREATE_VIDEOS_FOR_USE(state, payload) {
      state.VIDEOS = payload;
      console.log(state.VIDEOS);
    },
    GET_YOU_ISLIKED(state, payload) {
      state.isYouLiked = payload;
      console.log(state.isYouLiked);
    },
    GET_ROU_ISLIKED(state, payload) {
      state.isRouLiked = payload;
      console.log(state.isRouLiked);
    },
    GET_COMM_YOU(state, payload) {
      state.commsYou = payload;
    },
    GET_USER_INFO(state, payload) {
      state.userInfo = payload;
    },
    GET_PROFILEPATH(state, payload) {
      console.log(typeof payload);
      state.profilePath = payload;
      console.log(state.profilePath);
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
      const API_URL = `${REST_API}/meal/info`;
      axios({
        url: API_URL,
        method: "POST",
        data: meal,
      })
        .then(() => {
          console.log("식단 등록 성공");
          commit("CREATE_MEAL", true);
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
          console.log(typeof res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getVideo({ commit }, videoId) {
      console.log("비디오 상세정보 가져올게" + videoId);
      const API_URL = `${REST_API}/youtube/info/${videoId}`;
      axios({
        url: API_URL,
        method: "GET",
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          commit("GET_VIDEO", res.data);
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
    getGroup({ commit }, id) {
      const API_URL = `${REST_API}/group/info/${id}`;
      axios({
        url: API_URL,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          commit("GET_GROUP", res.data.groupInfo);
          delete res.data.groupInfo;
          commit("GET_MEM", res.data);
          commit("GET_KEYS", Object.keys(res.data));
        })
        .catch((err) => {
          console.log(err);
        });
    },
    createGroup({ commit }, group) {
      const API_URL = `${REST_API}/group/info`;
      axios({
        url: API_URL,
        method: "POST",
        data: group,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          res;
          commit("CREATE_ROUTINE", true);
          router.push({ name: "challengeList" });
        })
        .catch((err) => {
          console.log(err.toJSON().status);
          commit("CREATE_ROUTINE", false);
        });
    },
    enrollGroup({ dispatch }, user) {
      const API_URL = `${REST_API}/user/info/2`;
      axios({
        url: API_URL,
        method: "POST",
        params: user,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then(() => {
          console.log("그룹가입신청 완료");
          dispatch("getGroups");
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
    getRoutine({ commit }, id) {
      const API_URL = `${REST_API}/routine/info/${id}`;
      axios({
        url: API_URL,
        method: "GET",
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          commit("GET_ROUTINE", res.data[0]);
          commit("GET_WORKOUTS", res.data.slice(1));
        })
        .catch((err) => {
          console.log(err);
        });
    },
    createRoutine({ commit }, list) {
      console.log("루틴 등록 시작");
      const API_URL = `${REST_API}/routine/info`;
      axios({
        url: API_URL,
        method: "POST",
        data: list,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          console.log(res);
          console.log("일단 실행은 성공");
          commit("CREATE_ROUTINE", true);
          router.push({ name: "routineList" });
        })
        .catch((err) => {
          console.log("실행되긴 했는데 못 들어감ㄴ");
          console.log(err.toJSON().status);
          commit("CREATE_ROUTINE", false);
        });
    },
    userLogin({ commit, dispatch }, user) {
      const API_URL = `${REST_API}/user/login`;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          commit("USER_LOGIN", res.data.id);
          sessionStorage.setItem("access-token", res.data["access-token"]);
          sessionStorage.setItem("USER_ID", res.data.id);
          dispatch("userIsLogin");
          dispatch("getUserInfo", res.data.id);
          router.push({ name: "main" });
        })
        .catch((err) => {
          console.log(err);
          console.log(user);
        });
    },
    userIsLogin({ commit }) {
      let userId = sessionStorage.getItem("USER_ID");
      if (userId) {
        commit("USER_ISLOGIN", true);
      } else {
        commit("USER_ISLOGIN", false);
      }
    },
    getUserInfo({ commit, dispatch }, userId) {
      const API_URL = `${REST_API}/user/info/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          commit("GET_USER_INFO", res.data);
          console.log("user 데이터 가져왔다링");
          console.log(res.data);
          if (res.data.filepath) {
            console.log("이제 path 바꿀게");
            dispatch("getProfilePath", userId);
          }
        })
        .catch((err) => {
          err;
          console.log("유저 정보 못가져옴............");
        });
    },
    createUser({ commit }, user) {
      const API_URL = `${REST_API}/user/register`;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then((res) => {
          console.log(res);
          commit("CREATE_USER", 1);
          router.push({ name: "login" });
        })
        .catch((err) => {
          console.log(err.toJSON().status);
          console.log(user);
          if (err.toJSON().status == 409) {
            commit("CREATE_USER", 2);
          } else {
            commit("CREATE_USER", 0);
          }
        });
    },
    updateUserInfo({ dispatch }, user) {
      const API_URL = `${REST_API}/user/info/info`;
      axios({
        url: API_URL,
        method: "PUT",
        data: user,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          console.log(res);
          console.log("유저 데이터 업데이트 성공");
          let userId = sessionStorage.getItem("USER_ID");
          dispatch("getUserInfo", userId);
          router.push({ name: "myPage" });
        })
        .catch((err) => {
          console.log("유저 업데이트 못함....");
          console.log(err.toJSON());
          console.log(err.toJSON().status);
          console.log(user);
        });
    },
    updateUserInfoProfile({ dispatch }, user) {
      let userID = sessionStorage.getItem("USER_ID");
      const API_URL = `${REST_API}/user/info/${userID}`;
      axios({
        url: API_URL,
        method: "PUT",
        data: user,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          console.log(res);
          console.log("유저 프로필 업데이트 성공");
          dispatch("getUserInfo", userID);
          router.push({ name: "myPage" });
        })
        .catch((err) => {
          console.log("유저 프로필 업데이트 못함....");
          console.log(err.toJSON());
          console.log(err.toJSON().status);
          console.log(user);
        });
    },
    logout({ commit }) {
      commit("LOGOUT");
    },
    getProfilePath({ commit }, userId) {
      commit("GET_PROFILEPATH", userId);
    },
    createVideosForUse({ commit }) {
      console.log("실행되었음");
      let videosUseful = [];
      console.log(this.state.videos);
      for (let video in this.videos) {
        for (let ex in this.exercises) {
          if (video.exerciseNo == ex.exerciseNo) {
            let temp = {
              title: video.title,
              videoId: video.videoId,
              channelName: video.channelName,
              part: ex.exercisePart,
              workout: ex.exerciseName,
              viewCnt: video.viewCnt,
            };
            console.log("나 일단 만들어지긴 했음");
            console.log(temp);
            videosUseful.push(temp);
          }
        }
      }
      commit("CREATE_VIDEOS_FOR_USE", videosUseful);
    },
    getIsLikedYou({ commit }, liked) {
      const API_URL = `${REST_API}/youtube/liked/check`;
      axios({
        url: API_URL,
        method: "POST",
        data: liked,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          if (res.data.status == "true") {
            commit("GET_YOU_ISLIKED", true);
          } else {
            commit("GET_YOU_ISLIKED", false);
          }
        })
        .catch((err) => {
          console.log(err.toJSON());
        });
    },
    getIsLikedRou({ commit }, liked) {
      const API_URL = `${REST_API}/routine/liked/check`;
      axios({
        url: API_URL,
        method: "POST",
        data: liked,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          if (res.data.status == "true") {
            commit("GET_ROU_ISLIKED", true);
          } else {
            commit("GET_ROU_ISLIKED", false);
          }
        })
        .catch((err) => {
          console.log(err.toJSON());
        });
    },
    addLikedYou({ dispatch }, liked) {
      const API_URL = `${REST_API}/youtube/liked/add`;
      let JSONparsed = JSON.parse(liked);
      axios({
        url: API_URL,
        method: "POST",
        data: liked,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          // commit("GET_ISLIKED", true);
          res;
          dispatch("getIsLikedYou", liked);
          dispatch("getVideo", JSONparsed.videoId);
        })
        .catch((err) => {
          console.log(err.toJSON());
        });
    },
    delLikedYou({ dispatch }, liked) {
      const API_URL = `${REST_API}/youtube/liked/del`;
      let JSONparsed = JSON.parse(liked);
      axios({
        url: API_URL,
        method: "POST",
        data: liked,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          res;
          dispatch("getIsLikedYou", liked);
          dispatch("getVideo", JSONparsed.videoId);
        })
        .catch((err) => {
          console.log(err.toJSON());
        });
    },
    addLikedRou({ dispatch }, liked) {
      const API_URL = `${REST_API}/routine/liked/add`;
      let JSONparsed = JSON.parse(liked);
      axios({
        url: API_URL,
        method: "POST",
        data: liked,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          // commit("GET_ISLIKED", true);
          res;
          dispatch("getIsLikedRou", liked);
          dispatch("getRoutine", JSONparsed.routineNo);
        })
        .catch((err) => {
          console.log(err.toJSON());
        });
    },
    delLikedRou({ dispatch }, liked) {
      const API_URL = `${REST_API}/routine/liked/del`;
      let JSONparsed = JSON.parse(liked);
      axios({
        url: API_URL,
        method: "POST",
        data: liked,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
          "Content-Type": "application/json",
        },
      })
        .then((res) => {
          res;
          dispatch("getIsLikedRou", liked);
          dispatch("getRoutine", JSONparsed.routineNo);
        })
        .catch((err) => {
          console.log(err.toJSON());
        });
    },
    getCommentsYou({ commit }, id) {
      const API_URL = `${REST_API}/youtube/comm/${id}`;
      axios({
        url: API_URL,
        method: "GET",
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then((res) => {
          console.log("댓글 가져오는 부분")
          for(let item of res.data)
            item.status = false;
          commit("GET_COMM_YOU", res.data);
        })
        .catch((err) => {
          console.log("미안 댓글 못가져옴");
          console.log(err.toJSON());
        });
    },
  },
  modules: {},
});
