package persist.json;

import core.model.Auction;
import core.model.AuctionBid;
import core.model.Store;
import core.model.User;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class JSONAuction implements JSONDeserializer<Auction> {
    public int minimumBid;
    public int minimumBidIncrement;
    public int buyoutPrice;
    public Instant auctionStartTime, auctionEndTime;
    public JSONAuctionBid[] bidHistory;

    public JSONAuction() {
        
    }

    public JSONAuction(Auction auction) {
        minimumBid = auction.minimumBid;
        minimumBidIncrement = auction.minimumBidIncrement;
        buyoutPrice = auction.buyoutPrice;
        auctionStartTime = auction.auctionStartTime;
        auctionEndTime = auction.auctionEndTime;
        bidHistory = auction.bidHistory.stream().map(JSONAuctionBid::new).toArray(JSONAuctionBid[]::new);
    }

    @Override
    public Auction deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap) {
        return new Auction(minimumBid, minimumBidIncrement, buyoutPrice, auctionStartTime, auctionEndTime,
                Arrays.stream(bidHistory)
                        .map(bid -> bid.deserialize(idUserMap, idStoreMap))
                        .collect(Collectors.toCollection(ArrayList::new)));
    }
}
