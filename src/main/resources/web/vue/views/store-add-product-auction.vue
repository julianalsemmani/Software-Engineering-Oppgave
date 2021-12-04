<template id="store-add-product-auction">
  <div id="store-page">
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main class="form-signin">
      <form>
      <h1 class="h3 mb-3 fw-normal">Add Product Auction</h1>

      <div class="form-floating">
        <label for="floatingProdName">Product name</label>
        <input type="text" class="form-control my-3" id="floatingProdName" placeholder="Product name">
      </div>
      <div class="form-floating">
        <label for="floatingProdDesc">Product description</label>
        <textarea type="text" style="height: 105px;" class="form-control my-3" id="floatingProdDesc" placeholder="Product description"></textarea>
      </div>
      <div class="form-floating">
        <label for="floatingImage">Product picture</label>
        <input type="text" class="form-control my-3" id="floatingImage" placeholder="https://example.com">
      </div>
      <div class="form-floating">
        <label for="floatingStartPrice">Start price</label>
        <input type="number" class="form-control my-3" id="floatingStartPrice" placeholder="$4.99">
      </div>
      <div class="form-floating">
        <label for="floatingBuyoutPrice">Buyout price</label>
        <input type="number" class="form-control my-3" id="floatingBuyoutPrice" placeholder="$99.99">
      </div>
      <div class="form-floating">
        <label for="floatingIncreasePrice">Minimum increase price</label>
        <input type="number" class="form-control my-3" id="floatingIncreasePrice" placeholder="$0.99">
      </div>
      <div class="form-floating">
        <label for="floatingStartPrice">Start date</label>
        <input type="text" class="form-control my-3" id="floatingStartTime" placeholder="DD/MM/YYYY" onfocus="(this.type='date')">
      </div>
      <div class="form-floating">
        <label for="floatingEndTime">End date</label>
        <input type="text" class="form-control my-3" id="floatingEndTime" placeholder="DD/MM/YYYY" onfocus="(this.type='date')">
      </div>

      <button class="w-100 btn btn-lg btn-primary" type="submit">Add product</button>
      </form>
    </main>
    <store-footer v-bind:store="store"></store-footer>
  </div>
</template>

<script>
Vue.component("store-add-product-auction", {
  template: "#store-add-product-auction",
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



