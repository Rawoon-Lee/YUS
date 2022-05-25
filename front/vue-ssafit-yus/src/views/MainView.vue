<template>
  <div class="main">
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333"
    >
      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide>
        <template #img>
          <router-link to="/routine">
            <img
              class="d-block img-fluid w-100"
              width="1024"
              height="480"
              src="@/assets/routine.jpg"
              alt="image slot"
            />
          </router-link>
        </template>
      </b-carousel-slide>
      <b-carousel-slide>
        <template #img>
          <router-link to="/challenge">
            <img
              class="d-block img-fluid w-100"
              width="1024"
              height="480"
              src="@/assets/challenge.jpg"
              alt="image slot"
            />
          </router-link>
        </template>
      </b-carousel-slide>
      <b-carousel-slide>
        <template #img>
          <router-link to="/meal">
            <img
              class="d-block img-fluid w-100"
              width="1024"
              height="480"
              src="@/assets/meal.jpg"
              alt="image slot"
            />
          </router-link>
        </template>
      </b-carousel-slide>
      <b-carousel-slide>
        <template #img>
          <router-link to="/exercise/all">
            <img
              class="d-block img-fluid w-100"
              width="1024"
              height="480"
              src="@/assets/youtube.jpg"
              alt="image slot"
            />
          </router-link>
        </template>
      </b-carousel-slide>
    </b-carousel>

    <p class="mt-4">
      Slide #: {{ slide }}<br />
      Sliding: {{ sliding }}
    </p>
  </div>
</template>

<script>
export default {
  name: "MainView",
  components: {
    // HelloWorld,
  },
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
  // methods: {
  //   onSlideStart(slide) {
  //     this.sliding = true;
  //   },
  //   onSlideEnd(slide) {
  //     this.sliding = false;
  //   },
  // },
};
</script>
