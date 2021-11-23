<template id="prototype-hub">
  <div>
    <h1>Butikker</h1>
    <ul>
      <li v-for="store in stores" >
        <a :href="`/stores/${store.id}`">{{store.storeName}}</a>
      </li>
    </ul>
    <h1>Brukere</h1>
    <ul>
      <li v-for="store in stores" >
        <a :href="`/stores/${store.id}`">{{store.storeName}}</a>
      </li>
    </ul>
  </div>
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
      .then(res => {
        this.stores = res
        console.log(res)
      })

  }
})
</script>