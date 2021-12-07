<template id="store-contact">
  <div id="store-page">
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main id="main">
      <div class="container">
        <div style="text-align:center">
          <h2>Contact Us</h2>
          <p>Tell us about your issue and we will get back to you as soon as possible:</p>
        </div>
        <div class="row">
          <section class="column">
            <address class="storedetails">
              <h2>Store Contact Details</h2>
              <p>Phone: {{ this.store.phoneNumber }}</p>
              <p>E-mail: {{ this.store.owner.email }}</p>
            </address>
          </section>
          <div class="column">
            <form action="#">
              <label for="fname">First Name</label>
              <input type="text" id="fname" name="firstname" placeholder="Your first name..">
              <label for="lname">Last Name</label>
              <input type="text" id="lname" name="lastname" placeholder="Your last name..">
              <label for="email">E-mail</label>
              <input type="text" id="email" name="email" placeholder="Your e-mail..">
              <label for="subject">Subject</label>
              <select id="subject" name="subject">
                <option value="choose option">Choose your subject</option>
                <option value="account issues">Account Issues</option>
                <option value="privacy issues">Privacy Issues</option>
                <option value="orders">Orders</option>
                <option value="shipping">Shipping</option>
                <option value="product">Products</option>
                <option value="payment">Payment</option>
                <option value="other">Other</option>
              </select>
              <label for="yourmessage">Your Message</label>
              <textarea id="yourmessage" name="yourmessage" placeholder="Tell us about your issue..." style="height:170px"></textarea>
              <input type="submit" value="Submit">
            </form>
          </div>
        </div>
      </div>
    </main>
    <store-footer v-bind:store="store"></store-footer>
  </div>
</template>

<script>
Vue.component("store-contact", {
  template: "#store-contact",
  data: () => ({
    store: {}
  }),
  created() {
    const storeId = this.$javalin.pathParams["store-id"];
    fetch(`/api/stores/${storeId}`)
        .then(res => res.json())
        .then(res => { this.store = res; console.log(res); })
        .catch(() => alert("Data not found"));
  }
})
</script>

<style scoped>

</style>