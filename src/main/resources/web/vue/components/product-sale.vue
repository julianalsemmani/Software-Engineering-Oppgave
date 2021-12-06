<template id="product-sale">
  <section>
    <p>Sale type: Auction</p>
    <div class="auction-info">
      <p>Price: {{sale.price}} NOK</p>
      <button id="bid-submit" class="buttonP" type="buttonP" v-on:click="buy()">
        <i class="fas fa-dollar-sign"></i>
        Buy</button>
    </div>
    <div v-else>
      Auction has ended
    </div>
  </section>
</template>

<script>
Vue.component("product-sale", {
  template: "#product-sale",
  data: () => ({
    sale: {},
  }),
  props: {
    product: Object
  },
  methods: {
  },
  watch: {
  },
  mounted() {
    this.sale = this.product.saleMethod.sale
  }
})
</script>

<style scoped>
#time-remaining {
  color: orangered;
  font-size: xx-large;
  margin-bottom: 0;
}

.auction-info p {
  margin: 3px 0;
}

.invalid-input {
  background-color: lightcoral;
}
</style>