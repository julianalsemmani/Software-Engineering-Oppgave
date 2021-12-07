<template id="store-navbar">
  <nav id="store-navbar">
    <ul>
      <span>
        <li><a :href="`/stores/${this.store.id}`" class="button-box">Home</a></li>
        <li><a :href="`/stores/${this.store.id}/products`" class="button-box">Products</a></li>
        <li><a :href="`/stores/${this.store.id}/contact`" class="button-box">Contact Us</a></li>
      </span>
      <span v-if="((this.owner == true || this.employee == true))">
        <li><a :href="`/stores/${this.store.id}/add-product-sale`" class="button-box">Add product sale</a></li>
        <li><a :href="`/stores/${this.store.id}/add-product-auction`" class="button-box">Add product auction</a></li>
      </span>
    </ul>
  </nav>
</template>

<script>
Vue.component("store-navbar", {
  template: "#store-navbar",
  data: {
    me: null,
    owner: false,
    employee: false
  },
  props: {
    store: Object
  },
  created() {
    this.me = this.$javalin.state.me
    this.owner = this.$javalin.state.isOwner
    this.employee = this.$javalin.state.isEmployee
    console.log(this.$javalin.state)
  }
})
</script>

<style scoped>

</style>