<template>
  <div>
    <v-card class="mx-auto" color="#F5F5F5" max-width="700  " v-if="classNo == 0">
      <v-card-subtitle>
        <v-list-item-avatar>
            <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
        </v-list-item-avatar>
        <v-list-item-title>{{ userId }}</v-list-item-title>
      </v-card-subtitle>
      <v-card-actions>
        <v-list-item>
          <v-card-text class="text-h5 font-weight-bold">
            {{ comm }}
          </v-card-text>
          <v-card-text class="text-h5 font-weight-bold">
            {{ regDate }}
          </v-card-text>
        </v-list-item>
        <v-btn color="orange" text> 답글 달기 </v-btn>
        <v-btn color="orange" text v-if="user == userId"> 삭제하기 </v-btn>
      </v-card-actions>
      <v-card
        class="mx-auto"
        color="#E0E0E0"
        light
        max-width="400"
        v-if="classNo == 1"
      >
        <v-card-text>
          <v-list-item-title>{{ userId }}</v-list-item-title>
        </v-card-text>
        <v-list-item class="grow">
          <v-list-item-avatar color="indigo">
            <v-icon dark> mdi-account-circle </v-icon>
          </v-list-item-avatar>
          <!-- <v-list-item-avatar color="grey darken-3">
            <v-img
              class="elevation-5"
              alt="프로필"
              src="@/assets/profile-default.png"
            ></v-img>
          </v-list-item-avatar> -->
          <v-card-text class="text-h5 font-weight-bold">
            {{ comm }}
          </v-card-text>
          <v-card-actions> </v-card-actions>
        </v-list-item>
      </v-card>
    </v-card>
    <br />
  </div>
</template>

<script>
export default {
  props: {
    comm: String,
    userId: String,
    regDate: String,
    classNo: Number,
  },
  data() {
    return {
      user: sessionStorage.getItem("USER_ID"),
    };
  },
};
</script>

<style></style>
