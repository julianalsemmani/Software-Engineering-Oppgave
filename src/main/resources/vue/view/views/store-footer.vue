<template id="store-footer">
  <footer id="footer">
    <p>Copyright &copy; 2020 Ressursside for Webutvikling</p>
    <p>store.owner.name</p>
    <address>
      <a :href="mailto:{{store.owner.email}}">store.owner.email</a>
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
