<template id="store-footer">
  <footer id="footer">
<!--    <p>Copyright &copy; 2020 Ressursside for Webutvikling</p>-->
    <address>
      <p>Copyright &copy; {{ this.store.storeName }}</p>
      <p>Owner: {{ this.store.owner.firstName }} {{ this.store.owner.lastName }}</p>
      <p>Address: {{ this.store.address }}, Phone Number: {{ this.store.phoneNumber }}</p>
      <a :href="`mailto:${this.store.owner.email}`">{{ this.store.owner.email }}</a>
    </address>
  </footer>
</template>

<script>
Vue.component("store-footer", {
  template: "#store-footer",
  props: {
    store: Object
  }
})
</script>
