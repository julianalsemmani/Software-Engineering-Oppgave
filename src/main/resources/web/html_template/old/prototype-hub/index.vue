<template id="prototype-hub">
  <div>
    <navbar></navbar>

    <h1>Stores</h1>
    <a href="/register-store">Create new store</a>
    <ul>
      <li v-for="store in stores" >
        <a :href="`/stores/${store.id}`">{{store.storeName}}</a>
<!--        <p>Eier: {{users.find(user => user.id === store.owner).username}}</p>-->
      </li>
    </ul>
    <h1>Users</h1>
    <a href="/register-user">Create new user</a>
    <ul>
      <li v-for="user in users">
        <a :href="`/login/${user.id}`">Login as {{user.username}}</a>
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