<template>
  <div>
    <v-card class="mx-auto" max-width="344" outlined>
      <v-list-item three-line>
        <v-list-item-content>
          <div class="text-overline mb-4">{{ userId }}</div>
          <v-list-item-title class="text-h5 mb-1">
            {{ comm }}
          </v-list-item-title>
          <v-list-item-subtitle>{{ regDate }}</v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
      </v-list-item>

      <v-card-actions>
        <v-btn outlined rounded text> 답댓글 달기 </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {
  props: {
    comm: String,
    userId: String,
    regDate: String,
  },
};
</script>

<style></style>
