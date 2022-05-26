<template>
  <div>
    <div>
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="4000"
        controls
        indicators
        background="#ababab"
        style="text-shadow: 1px 1px 2px #333"
      >
        <!-- Slides with img slot -->
        <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
        <b-carousel-slide>
          <div>
            <div>
              <h1>다른 사람들은 어떤 루틴으로 운동하고 있을까?</h1>
              <p>
                나만의 루틴을 등록해서 공유하고, 다른 사람들의 루틴을 참고하며
                스스로에게 가장 잘 맞는 루틴을 찾을 수 있을 거에요
              </p>
            </div>
          </div>
          <template #img>
            <router-link to="/routine">
              <img
                class="d-block img-fluid w-100"
                width="1024"
                height="480"
                src="@/assets/routine.jpg"
                alt="image slot"
                style="height: 92vh"
              />
            </router-link>
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <div class="d-flex align-items-center justify-content-center">
            <div>
              <h1>같이 운동을 이어갈 최고의 멤버들을 구해보세요</h1>
              <p>
                매일 꾸준히 운동을 하기 힘드셨나요? 또는 혼자보다는 함께가 더
                좋으신가요? 서로를 격려해주며, 또는 경쟁해가며 열정적으로 운동에
                몰입하세요. 친구와 함께라면 가능합니다
              </p>
            </div>
          </div>
          <template #img>
            <router-link to="/challenge">
              <img
                class="d-block img-fluid w-100"
                width="1024"
                height="480"
                src="@/assets/challenge.jpg"
                alt="image slot"
                style="height: 92vh"
              />
            </router-link>
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <div class="d-flex align-items-center justify-content-center">
            <div>
              <h1>식단을 어떻게 짤지 막막하나요?</h1>
              <p>
                건강에 좋은 식단은 그만큼 많은 신경을 써야됩니다. 똑같은
                샐러드에 지쳤다면 다른 사용자들의 식단을 참고해보는 것은
                어떨까요? 좋은 아이디어를 찾을 수 있을지도요!
              </p>
            </div>
          </div>
          <template #img>
            <router-link to="/meal">
              <img
                class="d-block img-fluid w-100"
                width="1024"
                height="480"
                src="@/assets/meal.jpg"
                alt="image slot"
                style="height: 92vh"
              />
            </router-link>
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <div class="d-flex align-items-center justify-content-center">
            <div>
              <h1>운동을 하기 위해서는 운동으로 특화된 이 곳으로</h1>
              <p>
                운동하려 유튜브를 틀었지만, 알고리즘에 홀려 몇 시간을 날린적이
                있나요? 여기에는 인기 많은 운동 영상, 그리고 부위별로 운동과
                관련된 영상만 모아두었습니다. 운동에 집중하고 싶다면 이곳으로
                오세요
              </p>
            </div>
          </div>
          <template #img>
            <router-link to="/exercise/all">
              <img
                class="d-block img-fluid w-100"
                width="1024"
                height="480"
                src="@/assets/youtube.jpg"
                alt="image slot"
                style="height: 92vh"
              />
            </router-link>
          </template>
        </b-carousel-slide>
      </b-carousel>
    </div>
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
