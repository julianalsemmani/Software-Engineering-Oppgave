package web.dtos.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import core.model.*;
import web.dtos.product.auction.BidResponseBody;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseBody {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SaleMethodResponseBody {
        public static class AuctionResponseBody {
            public int minimumBid;
            public int minimumBidIncrement;
            public int buyoutPrice;
            public long auctionStartTime, auctionEndTime;
            public BidResponseBody[] bidHistory;
            public boolean hasEnded;

            public AuctionResponseBody(Auction auction) {
                minimumBid = auction.startBid;
                minimumBidIncrement = auction.minimumBidIncrement;
                buyoutPrice = auction.buyoutPrice;
                auctionStartTime = auction.auctionStartTime.toEpochMilli();
                auctionEndTime = auction.auctionEndTime.toEpochMilli();
                bidHistory = auction.bidHistory.stream().map(BidResponseBody::new).toArray(BidResponseBody[]::new);
                hasEnded = auction.hasEnded();
            }
        }

        public static class SaleResponseBody {
            public int price;

            public SaleResponseBody(Sale sale) {
                price = sale.price;
            }

        }

        public AuctionResponseBody auction;
        public SaleResponseBody sale;

        public SaleMethodResponseBody(SaleMethod saleMethod) {
            if(saleMethod instanceof Auction) {
                auction = new AuctionResponseBody((Auction) saleMethod);
            } else if(saleMethod instanceof Sale){
                sale = new SaleResponseBody((Sale) saleMethod);
            }
        }
    }

    public UUID id;
    public String name;
    public String productPicture;
    public SaleMethodResponseBody saleMethod;

    public ProductResponseBody(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.productPicture = product.productPicture;
        if(product.saleMethod != null) {
            this.saleMethod = new SaleMethodResponseBody(product.saleMethod);
        }
    }
}
