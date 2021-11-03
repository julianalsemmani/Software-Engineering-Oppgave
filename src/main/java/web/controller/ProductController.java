package web.controller;

import core.repository.StartUpRepository;
import io.javalin.http.Context;

public class ProductController {
    public final StartUpRepository startUpRepository;

    public ProductController(StartUpRepository startUpRepository) {
        this.startUpRepository = startUpRepository;
    }

    public void getAllProducts(Context context) {
        int storeId = Integer.parseInt(context.pathParam("###"));

    }

    public void getAProduct(Context context) {
        int storeId = Integer.parseInt(context.pathParam("###"));
        int productId = Integer.parseInt(context.pathParam("###"));
    }

    public void createProduct(Context context) {
        int storeId = Integer.parseInt(context.pathParam("###"));

    }

    public void updateProduct(Context context) {
        int storeId = Integer.parseInt(context.pathParam("###"));
        int productId = Integer.parseInt(context.pathParam("###"));
    }

    public void deleteProduct(Context context) {
        int storeId = Integer.parseInt(context.pathParam("###"));
        int productId = Integer.parseInt(context.pathParam("###"));
    }
}
