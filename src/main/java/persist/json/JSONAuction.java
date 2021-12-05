package persist.json;

import core.model.Auction;
import core.model.Store;
import core.model.User;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class JSONAuction implements JSONDeserializer<Auction> {
    public int startBid;
    public int minimumBidIncrement;
    public int buyoutPrice;
    public Instant auctionStartTime, auctionEndTime;
    public JSONAuctionBid[] bidHistory;

    public JSONAuction() {
        
    }

    public JSONAuction(Auction auction) {
        startBid = auction.startBid;
        minimumBidIncrement = auction.minimumBidIncrement;
        buyoutPrice = auction.buyoutPrice;
        auctionStartTime = auction.auctionStartTime;
        auctionEndTime = auction.auctionEndTime;
        bidHistory = auction.bidHistory.stream().map(JSONAuctionBid::new).toArray(JSONAuctionBid[]::new);
    }

    @Override
    public Auction deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap) {
        return new Auction(startBid, minimumBidIncrement, buyoutPrice, auctionStartTime, auctionEndTime,
                Arrays.stream(bidHistory)
                        .map(bid -> bid.deserialize(idUserMap, idStoreMap))
                        .collect(Collectors.toCollection(ArrayList::new)));
    }
}
