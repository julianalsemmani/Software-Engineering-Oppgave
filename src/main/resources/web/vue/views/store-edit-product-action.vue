<template id="store-edit-product-auction">
  <div id="store-page">
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main class="main-pos">
      <h1>Edit Product Auction</h1>
      <form class="form-box">
        <label for="floatingProdName">Product name</label>
        <input type="text" id="floatingProdName" placeholder="Product name" v-model="product.name">

        <!-- <label for="floatingProdDesc">Product description</label>
        <textarea type="text" id="floatingProdDesc" placeholder="Product description"></textarea> -->

        <label for="floatingImage">Product picture</label>
        <input type="text" id="floatingImage" v-model="product.productPicture">

        <label for="floatingStartPrice">Start price</label>
        <input type="number" id="floatingStartPrice" v-model="auction.startBid">

        <label for="floatingIncreasePrice">Minimum increase price</label>
        <input type="number" id="floatingIncreasePrice" v-model="auction.minimumBidIncrement">

        <label for="floatingBuyoutPrice">Buyout price</label>
        <input type="number" id="floatingBuyoutPrice" v-model="auction.buyoutPrice">

      </form>
      <button v-on:click=updateProduct()>Update product</button>
    </main>
    <store-footer v-bind:store="store"></store-footer>
  </div>
</template>

<script>
Vue.component("store-edit-product-auction", {
  template: "#store-edit-product-auction",
  data: () => ({
    store: {},
    product: {},
    auction: {},
  }),
  async created() {
    await this.retrieveStore()
    await this.retrieveProduct()
    this.auction = this.product.saleMethod.auction
  },
  methods: {
    updateProduct: function () {
      const product = {
        name: floatingProdName.value,
        productPicture: floatingImage.value,
      }
      fetch(`/api/stores/${this.store.id}/products/${this.product.id}`, {
        method: 'PUT',
        body: JSON.stringify(product)
      })
          .then(res => res.json())

      const auction = {
        startBid: floatingStartPrice.value,
        minimumBidIncrement: floatingIncreasePrice.value,
        buyoutPrice: floatingBuyoutPrice.value,
        auctionStartTime: this.auction.auctionStartTime,
        auctionEndTime: this.auction.auctionEndTime
      }
      fetch(`/api/stores/${this.store.id}/products/${this.product.id}/auction`, {
        method: 'PUT',
        body: JSON.stringify(auction)
      })
          .then(() => window.location.replace(`/stores/${this.store.id}/products/${this.product.id}`))
    },
    retrieveStore: async function () {
      const storeId = this.$javalin.pathParams["store-id"];
      await fetch(`/api/stores/${storeId}`)
            .then(res => res.json())
            .then(store => this.store = store)
    },
    retrieveProduct: async function () {
      const storeId = this.$javalin.pathParams["store-id"];
      const productId = this.$javalin.pathParams["product-id"];
      await fetch(`/api/stores/${storeId}/products/${productId}`)
          .then(res => res.json())
          .then(product => this.product = product)
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