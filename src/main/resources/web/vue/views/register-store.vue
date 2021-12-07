
<template id="register-store">
  <div id="container">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <form class="text-center">
    <h1 class="h3 mb-3 fw-normal">Register store</h1>
    <div class="form-floating">
      <input type="text" class="form-control" id="storeName" required>
      <label for="storeName">Store name</label>
    </div>
    <div class="form-floating">
      <input type="text" class="form-control" id="address" required>
      <label for="address">Address</label>
    </div>
    <div class="form-floating">
      <input type="tel" class="form-control" id="phoneNumber" required>
      <label for="phoneNumber">Phone number</label>
    </div>
  </form>

  <button class="btn btn-lg btn-primary" v-on:click=submitUser()>Register</button>
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
          .then(res => res.json())
          .then(newStore => window.location.replace(`/stores/${newStore.id}`))
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
