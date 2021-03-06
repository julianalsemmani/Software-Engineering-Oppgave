package no.hiof.web.controller;

import no.hiof.core.model.Store;
import no.hiof.core.model.User;
import no.hiof.core.service.Service;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.plugin.json.JavalinJson;
import no.hiof.web.dtos.store.PostStoreBody;
import no.hiof.web.dtos.store.PutStoreBody;
import no.hiof.web.dtos.store.StoreResponseBody;

import java.util.List;
import java.util.UUID;

public class StoreController {
    public final Service service;

    public StoreController(Service service) {
        this.service = service;
    }

    public void onGetAllStores(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            List<Store> store = service.repository.getAllStores();

            ctx.json(store.stream().map(StoreResponseBody::new).toArray());
        });
    }

    public void onGetStore(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("store-id", UUID.class).get();

            Store store = service.repository.getStoreById(id);

            if (store == null)
                throw new NotFoundResponse();

            ctx.json(new StoreResponseBody(store));
        });
    }

    public void onPostStore(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            User loggedInUser = ControllerUtils.getLoggedInUser(ctx, service.repository);
            if(loggedInUser == null)
                throw new UnauthorizedResponse();

            PostStoreBody body = JavalinJson.fromJson(ctx.body(), PostStoreBody.class);

            Store newStore = service.createStore(body.storeName, loggedInUser, body.address, body.phoneNumber);

            ctx.status(201).json(new StoreResponseBody(newStore));
        });
    }

    public void onPutStore(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("store-id", UUID.class).get();
            PutStoreBody body = JavalinJson.fromJson(ctx.body(), PutStoreBody.class);

            Store updatedStore = service.updateStore(id, body.storeName, service.repository.getUserById(body.owner), body.address, body.phoneNumber);

            if (updatedStore == null)
                throw new NotFoundResponse();

            ctx.status(200).json(new StoreResponseBody(updatedStore));
        });
    }

    public void onDeleteStore(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("store-id", UUID.class).get();

            Store deletedStore = service.deleteStore(id);

            ctx.status(200).json(new StoreResponseBody(deletedStore));
        });
    }
}
