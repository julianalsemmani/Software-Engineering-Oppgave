package persist.json;

import core.model.Auction;
import core.model.AuctionBid;
import core.model.Store;
import core.model.User;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JSONAuction implements JSONDeserializer<Auction> {
    public int minimumBid;
    public int minimumBidIncrement;
    public int buyoutPrice;
    public Instant auctionStartTime, auctionEndTime;
    public List<AuctionBid> bidHistory;

    public JSONAuction() {
        
    }

    public JSONAuction(Auction auction) {
        minimumBid = auction.minimumBid;
        minimumBidIncrement = auction.minimumBidIncrement;
        buyoutPrice = auction.buyoutPrice;
        auctionStartTime = auction.auctionStartTime;
        auctionEndTime = auction.auctionEndTime;
        bidHistory = auction.bidHistory;
    }

    @Override
    public Auction deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap) {
        return new Auction(minimumBid, minimumBidIncrement, buyoutPrice, auctionStartTime, auctionEndTime, bidHistory);
    }
}
