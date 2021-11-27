<template id="store-navbar">
  <nav id="navbar">
    <ul>
      <li><a :href="`/stores/${this.store.id}`" class="button-box">Home</a></li>
      <li><a :href="`/stores/${this.store.id}/products`" class="button-box">Products</a></li>
      <li><a :href="`/stores/${this.store.id}/contactus`" class="button-box">Contact Us</a></li>
    </ul>
  </nav>
</template>

<script>
Vue.component("store-navbar", {
  template: "#store-navbar",
  props: {
    store: Object
  }
})
</script>

<style scoped>

</style>