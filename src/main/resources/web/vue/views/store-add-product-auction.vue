<template id="store-add-product-auction">
  <div id="store-page">
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main class="main-pos">
      <h1>Add Product Auction</h1>
      <form class="form-box">
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

      </form>
      <button v-on:click=submitProduct()>Add product</button>
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
     submitProduct: async function () {
      const product = {
        name: floatingProdName.value,
        productPicture: floatingImage.value,
      }
      let productId;
      await fetch(`/api/stores/${this.store.id}/products`, {
        method: 'POST',
        body: JSON.stringify(product)
      })
        .then(res => res.json())
        .then(newProduct => productId = newProduct.id)

      const auction = {
        minimumBid: floatingStartPrice.value,
        minimumBidIncrement: floatingIncreasePrice.value,
        buyoutPrice: floatingBuyoutPrice.value,
        auctionStartTime: new Date(floatingStartTime.value).getTime(),
        auctionEndTime: new Date(floatingEndTime.value).getTime()
      }
      fetch(`/api/stores/${this.store.id}/products/${productId}/auction`, { 
        method: 'POST',
        body: JSON.stringify(auction)
      })
          .then(() => window.location.replace(`/stores/${this.store.id}/products/${productId}`))
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

