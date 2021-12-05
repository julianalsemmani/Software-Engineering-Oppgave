<template id="store-add-product-sale">
  <div id="store-page">
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main class="main-pos">
      <h1>Add Product Sale</h1>
        <form class="form-box">
          <label for="floatingProdName">Product name</label>
          <input type="text" id="floatingProdName" placeholder="Product name">
          
          <!-- <label for="floatingProdDesc">Product description</label>
          <textarea type="text" id="floatingProdDesc" placeholder="Product description"></textarea> -->
          
          <label for="floatingImage">Product picture</label>
          <input type="text" id="floatingImage" placeholder="https://example.com">
          
          <label for="floatingPrice">Price</label>
          <input type="number" id="floatingPrice" placeholder="Price">
        </form>
      <button v-on:click=submitProduct()>Add product</button>
    </main>
    <store-footer v-bind:store="store"></store-footer>
  </div>
</template>

<script>
Vue.component("store-add-product-sale", {
  template: "#store-add-product-sale",
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

      const sale = {
        price: floatingPrice.value,
      }
      fetch(`/api/stores/${this.store.id}/products/${productId}/sale`, { 
        method: 'POST',
        body: JSON.stringify(sale)
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

