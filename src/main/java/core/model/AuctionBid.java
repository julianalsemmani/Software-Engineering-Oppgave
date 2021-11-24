package core.model;

import java.time.Instant;

public class AuctionBid {
    public User bidder;
    public int bidPrice;
    public Instant bidTime;

    public AuctionBid(User bidder, int bidPrice, Instant bidTime) {
        this.bidder = bidder;
        this.bidPrice = bidPrice;
        this.bidTime = bidTime;
    }
}
