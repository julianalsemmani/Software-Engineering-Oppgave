<template id="prototype-hub">
  <div>

    <h1>Butikker</h1>
    <a href="/register-store">Lag ny butikk</a>
    <ul>
      <li v-for="store in stores" >
        <a :href="`/stores/${store.id}`">{{store.storeName}}</a>
        <p>Eier: {{users.find(user => user.id === store.owner).username}}</p>
      </li>
    </ul>
    <h1>Brukere</h1>
    <a href="/register-user">Lag ny bruker</a>
    <ul>
      <li v-for="user in users" >
        <a :href="`/login/${user.id}`">Log inn som {{user.username}}</a>
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
        console.log(res)
        this.stores = res
      })
    fetch('/api/users')
      .then(res => res.json())
      .then(res => {
        console.log(res)
        this.users = res
      })
  }
})
</script>