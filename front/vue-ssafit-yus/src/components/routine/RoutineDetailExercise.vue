<template>
  <div>
    <v-card class="mx-auto" max-width="344" outlined>
      <v-list-item four-line>
        <v-list-item-content>
          <div class="text-overline mb-4">운동</div>
          <v-list-item-title class="text-h5 mb-1">
            {{ exerciseName }}
          </v-list-item-title>
          <v-list-item-subtitle>set : {{ set }}</v-list-item-subtitle>
          <v-list-item-subtitle>rep : {{ rep }}</v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
      </v-list-item>

      <!-- <v-card-actions>
        <v-btn outlined rounded text> Button </v-btn>
      </v-card-actions> -->
    </v-card>
  </div>
</template>

<script>
export default {
  name: "RoutineDetailExercise",
  props: {
    exerciseName: String,
    set: Number,
    rep: Number,
  },
};
</script>

<style></style>
