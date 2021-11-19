package web.controller;

import core.model.Product;
import core.service.Service;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.product.PostProductBody;
import web.dtos.product.ProductResponseBody;
import web.dtos.product.PutProductBody;

import java.util.UUID;

public class ProductController {

    public final Service service;

    public ProductController(Service service) {
        this.service = service;
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

}
