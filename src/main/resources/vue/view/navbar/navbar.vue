<template id="navbar">
  <div>
    <section :v-if="$javalin.state.me">
      <span>{{$javalin.state.me.username}}</span>
    </section>
  </div>
</template>

<script>
Vue.component("navbar", {
  template: "#navbar"
})
</script>

<style scoped>
.navbar {

}
</style>