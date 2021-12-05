package web.dtos.product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

public class PostProductAuctionBody {
    @NotNull
    public int startBid;
    @NotNull
    public int minimumBidIncrement;
    @NotNull
    public int buyoutPrice;
    @NotNull
    @NotEmpty
    public Instant auctionStartTime;
    @NotNull
    @NotEmpty
    public Instant auctionEndTime;
}