package web.dtos.product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

public class PutProductAuctionBody {
    public int startBid;
    public int minimumBidIncrement;
    public int buyoutPrice;
    public long auctionStartTime;
    public long auctionEndTime;
}