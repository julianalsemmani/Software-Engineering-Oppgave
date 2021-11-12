package web.controller;

import core.model.Product;
import core.model.User;
import core.repository.Repository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.PostUserBody;
import web.dtos.PutUserBody;
import web.dtos.UserResponseBody;
import web.dtos.product.PostProductBody;
import web.dtos.product.ProductResponseBody;
import web.dtos.product.PutProductBody;

import java.util.UUID;

public class ProductController {
    public final Repository repository;

    public ProductController(Repository repository) {
        this.repository = repository;
    }

    public void onGetProduct(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("product-id", UUID.class).get();

            Product product = repository.getProductById(id);

            if (product == null)
                throw new NotFoundResponse();

            ctx.json(new ProductResponseBody(product));
        });
    }

    public void onPostProduct(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            PostProductBody body = JavalinJson.fromJson(ctx.body(), PostProductBody.class);
            Product newProduct = repository.createProduct(UUID.randomUUID(), body.name, body.productPicture);

            ctx.status(201).json(new ProductResponseBody(newProduct));
        });
    }

    public void onPutProduct(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("product-id", UUID.class).get();
            PutProductBody body = JavalinJson.fromJson(ctx.body(), PutProductBody.class);

            Product updatedProduct = repository.updateProduct(id, body.name, body.productPicture);

            if (updatedProduct == null)
                throw new NotFoundResponse();

            ctx.status(200).json(new ProductResponseBody(updatedProduct));
        });
    }

    public void onDeleteProduct(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("product-id", UUID.class).get();

            Product deletedProduct = repository.deleteProduct(id);

            ctx.status(200).json(new ProductResponseBody(deletedProduct));
        });
    }

}
