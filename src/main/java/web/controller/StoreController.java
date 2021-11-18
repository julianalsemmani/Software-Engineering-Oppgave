package web.controller;

import core.model.Store;
import core.repository.Repository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.store.PostStoreBody;
import web.dtos.store.PutStoreBody;
import web.dtos.store.StoreResponseBody;

import java.util.UUID;

public class StoreController {
    public final Repository repository;

    public StoreController(Repository repository) {
        this.repository = repository;
    }

    public void onGetStore(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("store-id", UUID.class).get();

            Store store = repository.getStoreById(id);

            if (store == null)
                throw new NotFoundResponse();

            ctx.json(new StoreResponseBody(store));
        });
    }

    public void onPostStore(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            PostStoreBody body = JavalinJson.fromJson(ctx.body(), PostStoreBody.class);
            Store newStore = repository.createStore(body.storeName, repository.getUserById(body.owner), body.address, body.phoneNumber);

            ctx.status(201).json(new StoreResponseBody(newStore));
        });
    }

    public void onPutStore(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("store-id", UUID.class).get();
            PutStoreBody body = JavalinJson.fromJson(ctx.body(), PutStoreBody.class);

            Store updatedStore = repository.updateStore(id, body.storeName, repository.getUserById(body.owner), body.address, body.phoneNumber);

            if (updatedStore == null)
                throw new NotFoundResponse();

            ctx.status(200).json(new StoreResponseBody(updatedStore));
        });
    }

    public void onDeleteStore(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("store-id", UUID.class).get();

            Store deletedStore = repository.deleteStore(id);

            ctx.status(200).json(new StoreResponseBody(deletedStore));
        });
    }
}
