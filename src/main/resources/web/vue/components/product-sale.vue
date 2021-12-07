<template id="product-sale">
  <section>
    <p>Sale type: Sale</p>
    <p>Price: {{sale.price}} NOK</p>
    <div v-if="sale.buyer == null" class="auction-info" >
      <button id="bid-submit" class="buttonP" type="buttonP" v-on:click="buy()">
        <i class="fas fa-dollar-sign"></i>
        Buy</button>
    </div>
    <div v-else>
      Sold
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
  watch: {
    product: function () {
      this.sale = this.product.saleMethod.sale
    }
  },
  methods: {
    buy: function () {
      const storeId = this.$javalin.pathParams["store-id"];
      fetch(`/api/stores/${storeId}/products/${this.product.id}/buy`, {
        method: 'POST'
      })
          .then(()=>this.$emit('buy'))
    }
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