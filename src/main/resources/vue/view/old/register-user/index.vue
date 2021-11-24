
<template id="register-user">
  <div id="container">
  <form class="text-center">
    <h1 class="h3 mb-3 fw-normal">Registrer bruker</h1>

    <div class="form-floating">
      <input type="text" class="form-control" id="username" required>
      <label for="username">Brukernavn</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="password" required>
      <label for="password">Passord</label>
    </div>
    <div class="form-floating">
      <input type="text" class="form-control" id="firstName" required>
      <label for="firstName">Fornavn</label>
    </div>
    <div class="form-floating">
      <input type="text" class="form-control" id="lastName" required>
      <label for="lastName">Etternavn</label>
    </div>
    <div class="form-floating">
      <input type="email" class="form-control" id="email" required>
      <label for="email">Epost</label>
    </div>
    <div class="form-floating">
      <input type="text" class="form-control" id="address" required>
      <label for="address">Adresse</label>
    </div>

    <button class="w-100 btn btn-lg btn-primary" type="submit" v-on:click=submitUser()>Registrer</button>
  </form>
  </div>
</template>

<script>
Vue.component("register-user", {
  template: "#register-user",
  data: () => ({
  }),
  created() {

  },
  methods: {
    submitUser: () => {
      const user = {
        username: username.value,
        password: password.value,
        firstName: firstName.value,
        lastName: lastName.value,
        email: email.value,
        address: address.value
      }
      fetch('/api/users', {
        method: 'POST',
        body: JSON.stringify(user)
      })
          .then(res => res.json())
          .then(res => console.log(res))
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
