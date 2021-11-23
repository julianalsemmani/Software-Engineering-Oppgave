<template>

</template>

<script>
Vue.component("prototype-hub", {
  template: "#prototype-hub",
  data: () => ({
    stores: [],
    users: []
  }),
  created() {
    fetch('/api/stores')
      .then(res => res.json())
      .then(res => console.log(res))

  }
})
</script>