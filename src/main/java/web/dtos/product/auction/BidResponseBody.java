package web.dtos.product.auction;

import core.model.AuctionBid;
import web.dtos.user.UserResponseBody;

public class BidResponseBody {
    public UserResponseBody bidder;
    public int bidPrice;
    public long bidTime;

    public BidResponseBody(AuctionBid bid) {
        bidder = new UserResponseBody(bid.bidder);
        bidPrice = bid.bidPrice;
        bidTime = bid.bidTime.toEpochMilli();
    }
}
