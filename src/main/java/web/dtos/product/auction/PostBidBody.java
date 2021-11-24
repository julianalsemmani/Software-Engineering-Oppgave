package web.dtos.product.auction;

import org.unbrokendome.jackson.beanvalidation.JsonValidated;

import javax.validation.constraints.NotNull;

@JsonValidated
public class PostBidBody {
    @NotNull
    public int bid;
}
