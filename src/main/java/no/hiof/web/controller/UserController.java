package no.hiof.web.controller;

import no.hiof.core.model.User;
import no.hiof.core.service.Service;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.plugin.json.JavalinJson;
import no.hiof.web.dtos.user.PostUserBody;
import no.hiof.web.dtos.user.PutUserBody;
import no.hiof.web.dtos.user.UserResponseBody;

import java.util.List;
import java.util.UUID;

public class UserController {
    private final Service service;

    public UserController(Service service) {
        this.service = service;
    }

    public void onLogin(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID userId = ctx.pathParam("user-id", UUID.class).get();

            if(service.repository.getUserById(userId) != null) {
                ctx.cookie("user", userId.toString());
                ctx.redirect("/");
            } else {
                throw new NotFoundResponse();
            }
        });
    }

    public void onGetAllUsers(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            List<User> users = service.repository.getAllUsers();

            ctx.json(users.stream().map(UserResponseBody::new).toArray());
        });
    }

    public void onGetMe(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, ()->{
            User me = ControllerUtils.getLoggedInUser(ctx, service.repository);
            if(me == null) throw new UnauthorizedResponse();
            ctx.json(new UserResponseBody(me));
        });
    }

    public void onGetUser(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("user-id", UUID.class).get();

            User user = service.repository.getUserById(id);

            if (user == null)
                throw new NotFoundResponse();

            ctx.json(new UserResponseBody(user));
        });
    }

    public void onPostUser(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            PostUserBody body = JavalinJson.fromJson(ctx.body(), PostUserBody.class);
            User newUser = service.createUser(body.username, body.password, body.firstName, body.lastName, body.address, body.email);

            ctx.status(201).json(new UserResponseBody(newUser));
        });
    }

    public void onPutUser(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("user-id", UUID.class).get();
            PutUserBody body = JavalinJson.fromJson(ctx.body(), PutUserBody.class);

            User updatedUser = service.updateUser(id, body.username, body.password, body.firstName,
                    body.lastName, body.address, body.email);

            if (updatedUser == null)
                throw new NotFoundResponse();

            ctx.status(200).json(new UserResponseBody(updatedUser));
        });
    }

    public void onDeleteUser(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("user-id", UUID.class).get();

            User deletedUser = service.deleteUser(id);

            ctx.status(200).json(new UserResponseBody(deletedUser));
        });
    }
}
