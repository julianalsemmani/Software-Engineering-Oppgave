
<template id="prototype-hub">
  <div>
    <h1>Users {{ me ? `(Logged in as ${me.username})` : '(Not logged in)' }}</h1>
    <ul>
      <li v-for="user in users">
        <a class="prolnk" :href="`/login/${user.id}`">Login as {{user.username}}</a>
      </li>
    </ul>
    <a class="probtn" href="/register-user">Create new user</a>
    <h1>Stores</h1>
    <ul>
      <li v-for="store in stores" >
        <a class="prolnk" :href="`/stores/${store.id}`">{{store.storeName}} - Owner: {{store.owner.firstName}} {{store.owner.lastName}}</a>
      </li>
    </ul>
    <a class="probtn" href="/register-store">Create new store</a>

  </div>
</template>

<script>
Vue.component("prototype-hub", {
  template: "#prototype-hub",
  data: () => ({
    stores: [],
    users: [],
    me: null
  }),
  created() {
    this.me = this.$javalin.state.me
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

<style scoped>
.probtn {
  margin-left: 3px;
  padding: 2px 10px;
  font-size: 18px;
  border: solid 1px black;
  border-radius: 5px;
  background-color: #d0cbd6;
}

.prolnk {
  font-size: large;
  text-decoration: underline;
  color: blue;
}
</style>