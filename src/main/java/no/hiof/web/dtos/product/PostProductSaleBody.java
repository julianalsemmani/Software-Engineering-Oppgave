package no.hiof.web.dtos.product;

import javax.validation.constraints.NotNull;

public class PostProductSaleBody {
    @NotNull
    public int price;
}
