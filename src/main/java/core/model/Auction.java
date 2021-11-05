package core.model;

import java.time.Instant;
import java.util.List;

public class Auction {
    public Product product;
    public Store store;
    public int minimumBid;
    public int minimumBidIncrement;
    public int buyoutPrice;
    public Instant auctionStartTime, auctionEndTime;
    public List<AuctionBid> bidHistory;

    public User winner;

    public AuctionBid getHighestBid() {
        if(bidHistory.size() > 0) {
            return bidHistory.get(bidHistory.size() - 1);
        }
        return null;
    }

    public boolean hasWinner() {
        return winner != null;
    }

    public boolean hasEnded() {
        return hasWinner() || Instant.now().isAfter(auctionEndTime);
    }
}
