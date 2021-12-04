<template id="store-add-product-sale">
  <div id="store-page">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main class="form-signin">
        <form>
          <h1 class="h3 mb-3 fw-normal">Add Product Sale</h1>

          <div class="form-floating">
            <input type="text" class="form-control my-3" id="floatingProdName" placeholder="Product name">
            <label for="floatingProdName">Product name</label>
          </div>
          <div class="form-floating">
            <textarea type="text" style="height: 105px;" class="form-control my-3" id="floatingProdDesc" placeholder="Product description"></textarea>
            <label for="floatingProdDesc">Product description</label>
          </div>
          <div class="form-floating">
            <input type="text" class="form-control my-3" id="floatingImage" placeholder="https://example.com">
            <label for="floatingImage">Product picture</label>
          </div>
          <div class="form-floating">
            <input type="number" class="form-control my-3" id="floatingPrice" placeholder="$9.99">
            <label for="floatingPrice">Price</label>
          </div>

          <button class="w-100 btn btn-lg btn-primary" type="submit">Publish sale</button>
        </form>
    </main>
    <store-footer v-bind:store="store"></store-footer>
  </div>
</template>

<script>
Vue.component("store-add-product-sale", {
  template: "#store-add-product-sale",
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
});
</script>

