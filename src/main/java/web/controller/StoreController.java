package web.controller;

import core.model.Store;
import core.model.User;
import core.repository.Repository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.PostUserBody;
import web.dtos.PutUserBody;
import web.dtos.UserResponseBody;
import web.dtos.store.PostStoreBody;
import web.dtos.store.StoreResponseBody;

public class StoreController {
    public final Repository repository;

    public StoreController(Repository repository) {
        this.repository = repository;
    }

    public void onGetStore(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            int id = ctx.pathParam("store-id", Integer.class).get();

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

    public void onPutUser(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            int id = ctx.pathParam("user-id", Integer.class).get();
            PutUserBody body = JavalinJson.fromJson(ctx.body(), PutUserBody.class);

            User updatedUser = repository.updateUser(id, body.username, body.password, body.firstName,
                    body.lastName, body.address, body.email);

            if (updatedUser == null)
                throw new NotFoundResponse();

            ctx.status(200).json(new UserResponseBody(updatedUser));
        });
    }

    public void onDeleteUser(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            int id = ctx.pathParam("user-id", Integer.class).get();

            User deletedUser = repository.deleteUser(id);

            ctx.status(200).json(new UserResponseBody(deletedUser));
        });
    }


}
