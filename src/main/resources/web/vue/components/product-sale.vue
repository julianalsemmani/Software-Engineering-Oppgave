<template id="product-sale">
  <section>
    <p>Sale type: Sale</p>
    <p>Price: {{sale.price}} NOK</p>
    <div v-if="sale.buyer == null && this.me != null && (this.owner == false && this.employee == false)" class="auction-info" >
      <button id="bid-submit" class="buttonP" type="buttonP" v-on:click="buy()">
        <i class="fas fa-dollar-sign"></i>
        Buy</button>
    </div>
    <div v-else-if="sale.buyer != null">
      Sold
    </div>
    <div v-else-if="this.owner == true && this.employee == true">
      You can not buy a product as a store owner/employee.
    </div>
    <div v-else>
      You have to login to bid/buy a product.
    </div>
    <div v-if="this.owner == true || this.employee == true" style="margin-top: 1.5rem;">
      <a :href="`/stores/${store.id}/products/${product.id}/edit-product-sale`" class="logbtn">Edit Product</a>
    </div>

  </section>
</template>

<script>
Vue.component("product-sale", {
  template: "#product-sale",
  data: () => ({
    sale: {},
    owner: false,
    employee: false
  }),
  props: {
    product: Object,
    store: Object
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
    this.me = this.$javalin.state.me
    this.owner = this.$javalin.state.isOwner
    this.employee = this.$javalin.state.isEmployee
  },
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