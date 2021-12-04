<template id="store-navbar">
  <nav id="store-navbar">
    <ul>
      <li><a :href="`/stores/${this.store.id}`" class="button-box">Home</a></li>
      <li><a :href="`/stores/${this.store.id}/products`" class="button-box">Products</a></li>
      <li><a :href="`/stores/${this.store.id}/contactus`" class="button-box">Contact Us</a></li>
      <li><a :href="`/stores/${this.store.id}/add-product-sale`" class="button-box">Add product sale</a></li>
      <li><a :href="`/stores/${this.store.id}/add-product-auction`" class="button-box">Add product auction</a></li>
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