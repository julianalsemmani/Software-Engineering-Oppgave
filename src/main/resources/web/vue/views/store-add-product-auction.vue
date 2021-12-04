<template id="store-add-product-auction">
  <div id="store-page">
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main class="main-pos">
      <h1>Add Product Auction</h1>
      <form method="post" class="form-box">
        <label for="floatingProdName">Product name</label>
        <input type="text" id="floatingProdName" placeholder="Product name">
      
        <!-- <label for="floatingProdDesc">Product description</label>
        <textarea type="text" id="floatingProdDesc" placeholder="Product description"></textarea> -->
      
        <label for="floatingImage">Product picture</label>
        <input type="text" id="floatingImage" placeholder="https://example.com">
      
      
        <label for="floatingStartPrice">Start price</label>
        <input type="number" id="floatingStartPrice" placeholder="$4.99">
      
        <label for="floatingIncreasePrice">Minimum increase price</label>
        <input type="number" id="floatingIncreasePrice" placeholder="$0.99">

        <label for="floatingBuyoutPrice">Buyout price</label>
        <input type="number" id="floatingBuyoutPrice" placeholder="$99.99">

        <label for="floatingStartPrice">Start date</label>
        <input type="text" id="floatingStartTime" placeholder="DD/MM/YYYY" onfocus="(this.type='date')">
      
        <label for="floatingEndTime">End date</label>
        <input type="text" id="floatingEndTime" placeholder="DD/MM/YYYY" onfocus="(this.type='date')">
      <button type="submit" v-on:click=submitProduct()>Add product</button>
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
  },
  methods: {
    submitProduct: () => {
      const product = {
        name: floatingProdName.value,
        productPicture: floatingImage.value,
        minimumBid: floatingStartPrice.value,
        minimumBidIncrement: floatingIncreasePrice.value,
        buyoutPrice: floatingBuyoutPric.value,
        auctionStartTime: floatingStartTime.value,
        auctionEndTime: floatingEndTime.value
      }
      fetch(`/api/stores/:store-id/products`, { 
        method: 'POST',
        body: JSON.stringify(product)
      })
          .then(res => res.json())
          .then(newProduct => window.location.replace(`/stores/${this.store.id}`))
    }
  }
})
</script>

<style scoped>

.form-box {
  max-height: auto;
  max-width: 200px;
}

.main-pos {
  padding-left: 500px;
  padding-top: 50px;
  padding-bottom: 50px;
}

</style>

