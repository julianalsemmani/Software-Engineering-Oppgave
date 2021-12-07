package web.controller;

import core.model.Product;
import core.model.User;
import core.service.Service;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.product.*;
import web.dtos.product.auction.PostBidBody;

import java.time.Instant;
import java.util.UUID;

public class ProductController {

    public final Service service;

    public ProductController(Service service) {
        this.service = service;
    }

    public void onPostBid(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID storeId = ctx.pathParam("store-id", UUID.class).get();
            UUID productId = ctx.pathParam("product-id", UUID.class).get();

            PostBidBody body = JavalinJson.fromJson(ctx.body(), PostBidBody.class);

            User bidder = ControllerUtils.getLoggedInUser(ctx, service.repository);
            if(bidder == null)
                throw new UnauthorizedResponse();

            if(service.doBid(bidder.id, storeId, productId, body.bid)) {
                ctx.status(200).result("Bid success");
            } else {
                ctx.status(400).result("Bid failed");
            }

        });
    }

    public void onGetProduct(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID storeId = ctx.pathParam("store-id", UUID.class).get();
            UUID productId = ctx.pathParam("product-id", UUID.class).get();

            Product product = service.getProductById(storeId, productId);

            if (product == null)
                throw new NotFoundResponse();

            ctx.json(new ProductResponseBody(product));
        });
    }

    public void onPostProduct(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID storeId = ctx.pathParam("store-id", UUID.class).get();
            System.out.print(storeId);
            PostProductBody body = JavalinJson.fromJson(ctx.body(), PostProductBody.class);

            Product newProduct = service.createProduct(storeId, body.name, body.productPicture);

            ctx.status(201).json(new ProductResponseBody(newProduct));
        });
    }

    public void onPutProduct(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID storeId = ctx.pathParam("store-id", UUID.class).get();
            UUID productId = ctx.pathParam("product-id", UUID.class).get();

            PutProductBody body = JavalinJson.fromJson(ctx.body(), PutProductBody.class);

            Product updatedProduct = service.updateProduct(storeId, productId, body.name, body.productPicture);

            if (updatedProduct == null)
                throw new NotFoundResponse();

            ctx.status(200).json(new ProductResponseBody(updatedProduct));
        });
    }

    public void onDeleteProduct(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID storeId = ctx.pathParam("store-id", UUID.class).get();
            UUID productId = ctx.pathParam("product-id", UUID.class).get();

            Product deletedProduct = service.deleteProduct(storeId, productId);

            ctx.status(200).json(new ProductResponseBody(deletedProduct));
        });
    }

    public void registerAuction(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID storeId = ctx.pathParam("store-id", UUID.class).get();
            UUID productId = ctx.pathParam("product-id", UUID.class).get();

            PostProductAuctionBody body = JavalinJson.fromJson(ctx.body(), PostProductAuctionBody.class);

            service.registerAuction(storeId, productId, body.startBid, body.minimumBidIncrement, body.buyoutPrice, Instant.ofEpochMilli(body.auctionStartTime), Instant.ofEpochMilli(body.auctionEndTime));

            ctx.status(201).result("");
        });
    }

    public void updateAuction(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID storeId = ctx.pathParam("store-id", UUID.class).get();
            UUID productId = ctx.pathParam("product-id", UUID.class).get();

            PutProductAuctionBody body = JavalinJson.fromJson(ctx.body(), PutProductAuctionBody.class);

            service.updateAuction(storeId, productId, body.startBid, body.minimumBidIncrement, body.buyoutPrice, Instant.ofEpochMilli(body.auctionStartTime), Instant.ofEpochMilli(body.auctionEndTime));

            ctx.status(200).result("");
        });
    }

    public void registerSale(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID storeId = ctx.pathParam("store-id", UUID.class).get();
            UUID productId = ctx.pathParam("product-id", UUID.class).get();

            PostProductSaleBody body = JavalinJson.fromJson(ctx.body(), PostProductSaleBody.class);

            service.registerSale(storeId, productId, body.price);

            ctx.status(201).result("");
        });
    }

    public void buyProduct(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID storeId = ctx.pathParam("store-id", UUID.class).get();
            UUID productId = ctx.pathParam("product-id", UUID.class).get();

            User buyer = ControllerUtils.getLoggedInUser(ctx, service.repository);
            if(buyer == null)
                throw new UnauthorizedResponse();

            if(service.doSale(storeId, productId, buyer.id)) {
                ctx.status(200).result("Success");
            } else {
                ctx.status(400).result("Failed to buy");
            }
        });
    }
}
