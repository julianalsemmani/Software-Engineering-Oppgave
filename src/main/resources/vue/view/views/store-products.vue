<template id="store-products">
  <div id="page-container">
    <store-header></store-header>
    <store-navbar></store-navbar>
    <main id="main">
      <article v-for="product in store.products">
        <section class="article-box" v-if="product.saleMethod?.sale" article>
          <p>{{product.name}}</p>
          <p>{{product.saleMethod.sale.price}}</p>
        </section>

        <section class="article-box" v-else-if="product.saleMethod?.auction">
          <p>Product name: {{product.name}}</p>
          <p>Minimum bid: {{product.saleMethod.auction.minimumBid}} NOK</p>
          <p>Minimum allowed bid: {{product.saleMethod.auction.minimumBidIncrement}} NOK</p>
          <p>Auction start time:{{new Date(product.saleMethod.auction.auctionStartTime).toLocaleString()}}</p>
          <p>Auction end time:{{new Date(product.saleMethod.auction.auctionEndTime).toLocaleString()}}</p>
        </section>

        <section class="article-box" v-else>
          <p>{{product.name}}</p>
          <p>Not for sale</p>
        </section>
      </article>
    </main>
    <store-footer></store-footer>
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

