<template id="store-edit-product-sale">
  <div id="store-page">
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main class="main-pos">
      <h1>Edit Product Sale</h1>
      <form class="form-box">
        <label for="floatingProdName">Product name</label>
        <input type="text" id="floatingProdName" v-model="product.name">

        <!-- <label for="floatingProdDesc">Product description</label>
        <textarea type="text" id="floatingProdDesc" placeholder="Product description"></textarea> -->

        <label for="floatingImage">Product picture</label>
        <input type="text" id="floatingImage" v-model="product.productPicture">

        <label for="floatingPrice">Price</label>
        <input type="number" id="floatingPrice" v-model="sale.price">
      </form>
      <button v-on:click=updateProduct()>Edit product</button>
    </main>
    <store-footer v-bind:store="store"></store-footer>
  </div>
</template>

<script>
Vue.component("store-edit-product-sale", {
    template: "#store-edit-product-sale",
    data: () => ({
      store: {},
      product: {},
      sale: {}
    }),
    async created() {
      await this.retrieveStore()
      await this.retrieveProduct()
      this.sale = this.product.saleMethod.sale
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

        const sale = {
          price: floatingPrice.value
        }
        fetch(`/api/stores/${this.store.id}/products/${this.product.id}/sale`, {
          method: 'PUT',
          body: JSON.stringify(sale)
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

