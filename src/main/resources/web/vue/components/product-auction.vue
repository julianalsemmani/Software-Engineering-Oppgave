<template id="product-auction">
  <section>
    <p>Sale type: Auction</p>
    <div class="auction-info" v-if="!auction.hasEnded && this.me != null && (this.owner == false && this.employee == false)">
      <p>Start bid: {{auction.startBid}} NOK</p>
      <p>Minimum bid: {{minimumBid}} NOK</p>
      <p>Bid increment: {{auction.minimumBidIncrement}} NOK</p>
      <p>Buyout: {{auction.buyoutPrice}} NOK</p>
      <p>Start time: {{new Date(auction.auctionStartTime).toLocaleString()}}</p>
      <p>End time: {{new Date(auction.auctionEndTime).toLocaleString()}}</p>
      <p id="time-remaining">{{ timeRemaining }}</p>

      <input id="bid-price" type="number" v-model="bidPrice" :class="{'invalid-input': bidPriceInvalid}"
             :min="minimumBid"
             :max="auction.buyoutPrice">
      <button id="bid-submit" class="buttonP" type="buttonP" v-on:click="doBid()">
        <i class="fas fa-dollar-sign"></i>
        Add bid</button>
    </div>
    <div v-else-if="auction.hasEnded">
      Auction has ended
    </div>
    <div v-else-if="this.owner == true || this.employee == true">
      You can not buy a product as a store owner/employee.
    </div>
    <div v-else>
      You have to login to bid/buy a product.
    </div>


    <ol>
      <li v-for="bid in auction.bidHistory">
        <span>Bid: {{bid.bidPrice}} NOK - </span>
        <span>Bidder: {{bid.bidder.username}} - </span>
        <span>Time: {{new Date(bid.bidTime).toLocaleString()}}</span>
      </li>
    </ol>
    <div v-if="this.owner || this.employee" style="margin-top: 1.5rem;">
      <a :href="`/stores/${store.id}/products/${product.id}/edit-product-auction`" class="logbtn">Edit Product</a>
    </div>

  </section>
</template>

<script>
Vue.component("product-auction", {
  template: "#product-auction",
  data: () => ({
    bidPrice: 0,
    bidPriceInvalid: true,
    timeRemaining: "",
    auction: {},
    minimumBid: 0,
    owner: false,
    employee: false,
    me: null
  }),
  props: {
    product: Object,
    store: Object
  },
  methods: {
    doBid: function () {
      const storeId = this.$javalin.pathParams["store-id"];
      fetch(`/api/stores/${storeId}/products/${this.product.id}/bid`, {
          method: 'POST',
          body: JSON.stringify({bid: this.bidPrice})})
        .then(()=>this.$emit('bid'))
    },
    updateAuction: function () {
      this.auction = this.product.saleMethod.auction
      this.auction.bidHistory = this.auction.bidHistory.reverse()

      this.minimumBid = this.auction.bidHistory.length > 0 ?
          this.auction.bidHistory[0].bidPrice + this.auction.minimumBidIncrement : this.auction.startBid

      this.bidPriceInvalid = this.bidPrice < this.minimumBid || this.bidPrice > this.auction.buyoutPrice
    }
  },
  watch: {
    product: function () {
      this.updateAuction()
    },
    bidPrice: function () {
      this.bidPriceInvalid = this.bidPrice < this.minimumBid || this.bidPrice > this.auction.buyoutPrice
    }
  },
  mounted() {
    this.updateAuction()

    this.bidPrice = this.minimumBid

    // From https://stackoverflow.com/questions/19700283/how-to-convert-time-in-milliseconds-to-hours-min-sec-format-in-javascript
    function msToTime(duration) {
      let milliseconds = Math.floor((duration % 1000) / 100),
          seconds = Math.floor((duration / 1000) % 60),
          minutes = Math.floor((duration / (1000 * 60)) % 60),
          hours = Math.floor((duration / (1000 * 60 * 60)) % 24);

      hours = (hours < 10) ? "0" + hours : hours;
      minutes = (minutes < 10) ? "0" + minutes : minutes;
      seconds = (seconds < 10) ? "0" + seconds : seconds;

      return hours + ":" + minutes + ":" + seconds + "." + milliseconds;
    }

    setInterval(
        ()=> {
          const endDate = new Date(this.auction.auctionEndTime);
          const msRemaining = endDate.getTime() - new Date().getTime()
          if(msRemaining > 0) {
            this.timeRemaining = msToTime(msRemaining)
          }
        },
        100
    )
  },
  created() {
    this.owner = this.$javalin.state.isOwner
    this.employee = this.$javalin.state.isEmployee
    this.me = this.$javalin.state.me
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