package no.hiof.web.dtos.product;

public class PutProductAuctionBody {
    public int startBid;
    public int minimumBidIncrement;
    public int buyoutPrice;
    public long auctionStartTime;
    public long auctionEndTime;
}