<template id="store-products">
  <div id="store-page">
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main id="main">
      <article v-for="product in store.products" :key="product.id">
        <section class="article-box" v-if="product.saleMethod?.sale" article>
          <img v-bind:src="product.productPicture" alt="product ordinare sale" width="200" height="200">
          <p>Sale type: Ordinary</p>
          <p>Product name: {{product.name}}</p>
          <p>Product price: {{product.saleMethod.sale.price}}</p>
        </section>

        <section class="article-box" v-else-if="product.saleMethod?.auction">
          <img v-bind:src="product.productPicture" alt="product auction" width="200" height="200">
          <p>Sale type: Auction</p>
          <p>Product name: {{product.name}}</p>
          <p>Current bid: {{product.saleMethod.auction.minimumBid}} NOK</p>
          <!-- <p>Minimum allowed bid: {{product.saleMethod.auction.minimumBidIncrement}} NOK</p>
          <p>Start time: {{new Date(product.saleMethod.auction.auctionStartTime).toLocaleString()}}</p>
          <p>End time: {{new Date(product.saleMethod.auction.auctionEndTime).toLocaleString()}}</p> -->
        </section>

        <section class="article-box" v-else>
          <img v-bind:src="product.productPicture" alt="product not for sale" width="200" height="200">
          <p>Sale type: Not for sale</p>
          <p>Product name: {{product.name}}</p>
          <p>Product price: Not for sale</p>
        </section>
      </article>
    </main>
    <store-footer v-bind:store="store"></store-footer>
  </div>
</template>

<script>
Vue.component("store-products", {
  template: "#store-products",
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

