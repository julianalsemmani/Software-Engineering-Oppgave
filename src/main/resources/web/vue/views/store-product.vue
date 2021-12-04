<template id="store-product">
  <div id="store-page">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <navbar></navbar>
    <store-header v-bind:store="store"></store-header>
    <store-navbar v-bind:store="store"></store-navbar>
    <main id="main">
      <article class="heroP">
        <section class="rowP">
          <section class="colP">

            <section class="sliderP">
              <section class="productP">

                <img :src="product.productPicture" alt="">
                <img :src="product.productPicture" alt="">
                <img :src="product.productPicture" alt="">
                <img :src="product.productPicture" alt="">

              </section>
              <section class="previewP">
                <img :src="product.productPicture" id="imagebox" alt="">
              </section>
            </section>

          </section>
          <section class="colP">

            <section class="contentP">
              <p class="brandP">Category: Sculpture</p>
              <h2 class="h2_p">{{ product.name }}</h2>
              <section class="ratingP">
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star-half-o"></i>
              </section>

              <product-auction v-bind:product="product"></product-auction>

            </section>

          </section>
        </section>

      </article>
    </main>
    <store-footer v-bind:store="store"></store-footer>
  </div>
</template>


<script>
Vue.component("store-product", {
  template: "#store-product",
  data: () => ({
    store: {},
    product: {}
  }),
  async created() {
    const storeId = this.$javalin.pathParams["store-id"];
    await fetch(`/api/stores/${storeId}`)
        .then(res => res.json())
        .then(res => { this.store = res; console.log(res); })
        .catch(() => alert("Store not found"));

    setInterval(async ()=> {
      const productId = this.$javalin.pathParams["product-id"];
      await fetch(`/api/stores/${storeId}/products/${productId}`)
          .then(res => res.json())
          .then(res => { this.product = res});
    }, 1000)
  }
});
</script>

<style scoped>
/*Product Info side style*/
.heroP{
  width: 100%;
}
.rowP{
  width: 90%;
  margin: auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.colP{
  flex-basis: 45%;
}
.sliderP{
  height: 80vh;
  display: flex;
}
.productP img{
  height: 19vh;
  margin-bottom: 9px;
  cursor: pointer;
  opacity: 0.6;
}

.productP img:hover{
  opacity: 1;
}


.previewP img{
  height: 100%;
  width: 100%;
}
.p_product{
  margin-bottom: 20px;
}
.brandP{
  background: #008000;
  width: fit-content;
  color: #fff;
  font-size: 12px;
  padding: 2px 5px;
}
.h2_p{
  font-size: 45px;
  color: #555;
  margin-bottom: 20px;
}
.ratingP{
  display: flex;
  height: 15px;
}
.ratingP .fa{
  color: #008000;
}
.priceP{
  color: #fe980f;
  font-size: 26px;
  font-weight: bold;
  padding-top: 10px;
}

.buttonP{
  color: #fff;
  font-size: 15px;
  outline: none;
  border: 0;
  border-radius: 5px;
  background: #fe980f;
  padding: 10px 15px;
  box-sizing: border-box;
  cursor: pointer;
}
.buttonP .fa{
  margin-right: 10px;
}
.relatedP{
  width: 90%;
  margin: 0 auto 40px;
}
.relatedP .rowP{
  width: 100%;
  height: auto;
}
.columnsP{
  flex-basis: 22%;
  height: 100%;
}
.itemsP img{
  width: 100%;
}
.detailsP{
  margin-top: 20px;
}
.detailsP p{
  font-size: 14px;
  margin-bottom: 10px;
}
.detailsP .ratingP{
  margin: 10px 0;
}
</style>