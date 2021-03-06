package no.hiof.persist.json;

import no.hiof.core.model.AuctionBid;
import no.hiof.core.model.Store;
import no.hiof.core.model.User;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class JSONAuctionBid implements JSONDeserializer<AuctionBid> {
    public UUID bidder;
    public int bidPrice;
    public Instant bidTime;

    public JSONAuctionBid() {}

    public JSONAuctionBid(AuctionBid bid) {
        bidder = bid.bidder.id;
        bidPrice = bid.bidPrice;
        bidTime = bid.bidTime;
    }

    @Override
    public AuctionBid deserialize(Map<UUID, User> idUserMap, Map<UUID, Store> idStoreMap) {
        return new AuctionBid(idUserMap.get(bidder), bidPrice, bidTime);
    }
}
