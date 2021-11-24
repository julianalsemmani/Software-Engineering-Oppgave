<template id="navbar">
  <div>
    <section>
      <span>{{loggedInUser.username}}</span>
    </section>
  </div>
</template>

<script>
Vue.component("navbar", {
  template: "#navbar",
  data: ({
    loggedIn: false,
    loggedInUser: {username: ""}
  }),
  created() {
    fetch('/api/me')
        .then(res => res.json())
        .then(res => {
          console.log({res})
          loggedInUser = res
          loggedIn = true
        })
        .catch(err => loggedIn = false);
  }
})
</script>

<style scoped>
.navbar {

}
</style>