
<template id="register-store">
  <div id="container">
  <form class="text-center">
    <h1 class="h3 mb-3 fw-normal">Registrer butikk</h1>
<!--    String storeName, User owner, String address, int phoneNumber-->
    <div class="form-floating">
      <input type="text" class="form-control" id="storeName" required>
      <label for="storeName">Butikknavn</label>
    </div>
    <div class="form-floating">
      <input type="text" class="form-control" id="address" required>
      <label for="address">Adresse</label>
    </div>
    <div class="form-floating">
      <input type="tel" class="form-control" id="phoneNumber" required>
      <label for="phoneNumber">Telefonnummer</label>
    </div>

    <button class="w-100 btn btn-lg btn-primary" v-on:click=submitUser()>Registrer</button>
  </form>
  </div>
</template>

<script>
Vue.component("register-store", {
  template: "#register-store",
  data: () => ({
  }),
  created() {

  },
  methods: {
    submitUser: () => {
      const store = {
        storeName: storeName.value,
        phoneNumber: phoneNumber.value,
        address: address.value,
      }
      fetch('/api/stores', {
        method: 'POST',
        body: JSON.stringify(store)
      })
          .then(() => window.location.replace('/'))
          .catch(err => console.log(err))

      return false; // This prevents page refresh
    }
  }
})
</script>

<style scoped>
  .bd-placeholder-img {
    font-size: 1.125rem;
    text-anchor: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
  }

  @media (min-width: 768px) {
    .bd-placeholder-img-lg {
      font-size: 3.5rem;
    }
  }

  #container {
    display: flex;
    align-items: center;
    justify-content: center;
    padding-top: 40px;
    padding-bottom: 40px;
    background-color: #f5f5f5;
  }

  .form-signin {
    width: 100%;
    max-width: 330px;
    padding: 15px;
    margin: auto;
  }

  .form-signin .checkbox {
    font-weight: 400;
  }

  .form-signin .form-floating:focus-within {
    z-index: 2;
  }

  .form-signin input[type="email"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  .form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }

</style>
