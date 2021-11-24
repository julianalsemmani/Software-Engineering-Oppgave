package web.dtos.product.auction;

import java.util.UUID;

public class PostBidBody {
    public UUID storeId;
    public UUID productId;
    public int bid;
}
