<template id="store-products">
  <div id="store-page">
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main id="main">
      <article v-for="product in store.products">
        <section class="article-box" v-if="product.saleMethod?.sale" article>
          <img src="https://cdn.pixabay.com/photo/2020/03/01/17/50/monalisa-4893660_640.jpg" alt="Girl in a jacket" width="200" height="200">
          <p>Sale type: Ordinary</p>
          <p>Product name: {{product.name}}</p>
          <p>Product price: {{product.saleMethod.sale.price}}</p>
        </section>

        <section class="article-box" v-else-if="product.saleMethod?.auction">
          <img src="https://imaginary.abcmedia.no/resize?width=980&interlace=true&url=https%3A%2F%2Fimaginary.abcmedia.no%2Fpipe%3Furl%3Dhttps%253A%252F%252Fabcnyheter.drpublish.aptoma.no%252Fout%252Fimages%252Farticle%252F%252F2013%252F11%252F06%252F195151679%252F1%252Foriginal%252F2234089.jpg" alt="Girl in a jacket" width="200" height="200">
          <p>Sale type: Auction</p>
          <p>Product name: {{product.name}}</p>
          <p>Minimum bid: {{product.saleMethod.auction.minimumBid}} NOK</p>
          <p>Minimum allowed bid: {{product.saleMethod.auction.minimumBidIncrement}} NOK</p>
          <p>Start time: {{new Date(product.saleMethod.auction.auctionStartTime).toLocaleString()}}</p>
          <p>End time: {{new Date(product.saleMethod.auction.auctionEndTime).toLocaleString()}}</p>
        </section>

        <section class="article-box" v-else>
          <img src="https://media.snl.no/media/7650/standard_Mona_Lisa.jpg" alt="Girl in a jacket" width="200" height="200">
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

