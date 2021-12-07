<template id="navbar">
  <nav id="navbar">
    <ul class="nav1buttons">
      <span v-if="this.me == null">
        <li class="logbtn"><a href="#">Login</a></li>
        <li class="signbtn"><a href="#">Sign up</a></li>
      </span>
      <span v-else>
        <li class="signbtn"><a hreF="">Log out</a></li>
      </span>
    </ul>
    <div v-if="this.me != null" class="dropdown" style="float:right;">
      <button class="dropbtn">My Account</button>
      <div class="dropdown-content">
        <a href="#">Account Settings</a>
        <a href="#">My Favourites</a>
        <a href="#">My Orders</a>
        <a href="#">My Bids</a>
        <a href="#">My Wallet</a>
        <a href="#">Purchase History</a>
      </div>
    </div>
  </nav>
</template>

<script>
Vue.component("navbar", {
  template: "#navbar",
  data: {
    me: null
  },
  created() {
    this.me = this.$javalin.state.me
    console.log(me)
  }
})
</script>

<style scoped>

/*Bruker Navigasjonsmeny*/
#navbar {
  grid-area: navbar;
  background-color: teal;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "buttons account"
}

.nav1buttons {
  grid-area: buttons;
}

/*DROPDOWN MENU (MY ACCOUNT)*/
.dropbtn {
  background-color: whitesmoke;
  float: right;
  padding: 12px;
  margin-right: 20px;
  margin-top: 10px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

.dropdown {
  grid-area: account;
  position: absolute;
  display: inline-block;
  right:0;
}

.dropdown-content {
  border: solid 1px black;
  margin: 60px;
  margin-right: 10px;
  display: none;
  position: relative;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 10px;
  text-decoration: none;
  text-align: left;
  display: block;
}

.dropdown-content a:hover {background-color: lightblue;}

.dropdown:hover .dropdown-content {display: block;}

/*ØVERSTE NAVBAR (LOG IN, MY ACCOUNT O.L)*/
#navbar ul {
  list-style:none;
}

#navbar li {
  display:inline;
}

#navbar a {
  color:black;
  text-decoration: none;
}

#navbar ul li a:hover {
  text-decoration: underline;
}

/*log in button*/
.logbtn {
  float: left;
  margin-left: 3px;
  padding: 2px 10px;
  font-size: 18px;
  border: solid 1px black;
  border-radius: 5px;
  background-color: #d0cbd6;
}

/*sign up button*/
.signbtn {
  float: left;
  margin-left: 15px;
  padding: 2px 10px;
  font-size: 18px;
  border: solid 1px black;
  border-radius: 5px;
  background-color: orange;
}

</style>