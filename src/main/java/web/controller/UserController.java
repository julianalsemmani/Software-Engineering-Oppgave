package web.controller;

import core.model.User;
import core.repository.Repository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.PostUserBody;
import web.dtos.PutUserBody;
import web.dtos.UserResponseBody;

import java.util.UUID;

public class UserController {
    public final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }


    public void onGetUser(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("user-id", UUID.class).get();

            User user = repository.getUserById(id);

            if (user == null)
                throw new NotFoundResponse();

            ctx.json(new UserResponseBody(user));
        });
    }

    public void onPostUser(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            PostUserBody body = JavalinJson.fromJson(ctx.body(), PostUserBody.class);
            User newUser = repository.createUser(body.username, body.password, body.firstName, body.lastName, body.address, body.email);

            ctx.status(201).json(new UserResponseBody(newUser));
        });
    }

    public void onPutUser(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("user-id", UUID.class).get();
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
            UUID id = ctx.pathParam("user-id", UUID.class).get();

            User deletedUser = repository.deleteUser(id);

            ctx.status(200).json(new UserResponseBody(deletedUser));
        });
    }
}
