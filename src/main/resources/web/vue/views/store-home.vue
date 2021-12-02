<template id="store-home">
  <div id="store-page">
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main id="main">
      <h1>About Us</h1>
      <p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
        sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
        nisi ut aliquip ex ea commodo consequat. 
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."</p>
    </main>
    <store-footer v-bind:store="store"></store-footer>
  </div>
</template>

<script>
Vue.component("store-home", {
  template: "#store-home",
  data: () => ({
    store: {}
  }),
  created() {
    const storeId = this.$javalin.pathParams["store-id"];
    fetch(`/api/stores/${storeId}`)
        .then(res => res.json())
        .then(res => { this.store = res; console.log(res); })
        .catch(() => alert("Data not found"));
  }
})
</script>