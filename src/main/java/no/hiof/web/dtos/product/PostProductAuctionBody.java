package no.hiof.web.dtos.product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostProductAuctionBody {
    @NotNull
    public int startBid;
    @NotNull
    public int minimumBidIncrement;
    @NotNull
    public int buyoutPrice;
    @NotNull
    @NotEmpty
    public long auctionStartTime;
    @NotNull
    @NotEmpty
    public long auctionEndTime;
}