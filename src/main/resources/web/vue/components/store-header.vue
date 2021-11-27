<template id="store-header">
  <header id="header">
    <h2>{{ this.store.storeName }}</h2>
  </header>
</template>

<script>
Vue.component("store-header", {
  template: "#store-header",
  props: {
    store: Object
  }
})
</script>

<style scoped>

</style>