package core.model;

import java.time.Instant;
import java.util.List;

public class Auction implements SaleMethod {
    public int startBid;
    public int minimumBidIncrement;
    public int buyoutPrice;
    public Instant auctionStartTime, auctionEndTime;
    public List<AuctionBid> bidHistory;

    public Auction(int startBid, int minimumBidIncrement, int buyoutPrice, Instant auctionStartTime, Instant auctionEndTime, List<AuctionBid> bidHistory) {
        this.startBid = startBid;
        this.minimumBidIncrement = minimumBidIncrement;
        this.buyoutPrice = buyoutPrice;
        this.auctionStartTime = auctionStartTime;
        this.auctionEndTime = auctionEndTime;
        this.bidHistory = bidHistory;
    }

    public AuctionBid getHighestBid() {
        if(bidHistory.size() > 0) {
            return bidHistory.get(bidHistory.size() - 1);
        }
        return null;
    }

    public boolean doBid(AuctionBid bid) {
        if(bid.bidPrice < startBid) {
            return false;
        }

        if(bidHistory.size() == 0 || bid.bidPrice >= getHighestBid().bidPrice + minimumBidIncrement) {
            bidHistory.add(bid);
            return true;
        }
        return false;
    }

    public User getWinner() {
        if(hasWinner()) {
            return getHighestBid().bidder;
        }
        return null;
    }

    public boolean hasWinner() {
        return hasEnded() && bidHistory.size() > 0;
    }

    public boolean hasEnded() {
        return Instant.now().isAfter(auctionEndTime)
                || (bidHistory.size() > 0 && getHighestBid().bidPrice >= buyoutPrice);
    }
}
