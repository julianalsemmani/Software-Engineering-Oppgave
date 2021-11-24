<template id="navbar">
  <nav class="navbar">
    <section>
      <span>{{loggedInUser.username}}</span>
    </section>
  </nav>
</template>

<script>
Vue.component("navbar", {
  template: "#navbar",
  data: ({
    loggedInUser: {}
  }),
  created() {

  }
})
</script>

<style scoped>
.navbar {

}
</style>