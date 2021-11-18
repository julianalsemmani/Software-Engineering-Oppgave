<template id="store-frontpage">
  <h1>{{store.name}}</h1>
  <section class="section-products">
    <div class="container">
      <div class="row justify-content-center text-center">
        <div class="col-md-8 col-lg-6">
          <div class="header">
            <h3>Featured Product</h3>
            <h2>Popular Products</h2>
          </div>
        </div>
      </div>
      <div class="row">
        <!-- Single Product -->
        <div class="col-md-6 col-lg-4 col-xl-3">
          <div id="product-1" class="single-product">
            <div class="part-1">
              <ul>
                <li><a href="#"><i class="fas fa-shopping-cart"></i></a></li>
                <li><a href="#"><i class="fas fa-heart"></i></a></li>
                <li><a href="#"><i class="fas fa-plus"></i></a></li>
                <li><a href="#"><i class="fas fa-expand"></i></a></li>
              </ul>
            </div>
            <div class="part-2">
              <h3 class="product-title">Here Product Title</h3>
              <h4 class="product-old-price">$79.99</h4>
              <h4 class="product-price">$49.99</h4>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
Vue.component("store-frontpage", {
  template: "#store-frontpage",
  data: () => ({
    store: {},
    products: [],
    storeId: "37abbfb1-06da-430a-9f89-b3189c9626cb"
  }),
  created() {
    fetch(`/api/stores/${this.storeId}`)
      .then(res => res.json())
      .then(res => { this.store = res; })
      .catch(() => alert("Data not found"))

  }
});
</script>

<style>

</style>