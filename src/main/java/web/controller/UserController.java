package web.controller;

import core.model.User;
import core.repository.StartUpRepository;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.PostUserBody;
import web.dtos.PutUserBody;
import web.dtos.UserResponseBody;

import javax.validation.ConstraintViolationException;

public class UserController {
    public final StartUpRepository startUpRepository;

    public UserController(StartUpRepository startUpRepository) {
        this.startUpRepository = startUpRepository;
    }

    public void onPostUser(Context ctx) {
        try {
            PostUserBody body = JavalinJson.fromJson(ctx.body(), PostUserBody.class);

            User newUser = startUpRepository.createUser(body.username, body.password, body.firstName, body.lastName, body.address, body.email);

            ctx.status(201).json(new UserResponseBody(newUser));
        } catch (ConstraintViolationException exception) {
            ctx.status(400).result(exception.getMessage());
        }
    }

    public void onPutUser(Context ctx) {
        try {
            int id = ctx.pathParam("user-id", Integer.class).get();
            PutUserBody body = JavalinJson.fromJson(ctx.body(), PutUserBody.class);

            User updatedUser = startUpRepository.updateUser(id, body.username, body.password, body.firstName,
                    body.lastName, body.address, body.email);

            ctx.status(200).json(new UserResponseBody(updatedUser));
        } catch (ConstraintViolationException exception) {
            ctx.status(400).result(exception.getMessage());
        }
    }

    public void onDeleteUser(Context ctx) {
        try {
            int id = ctx.pathParam("user-id", Integer.class).get();

            User deletedUser = startUpRepository.deleteUser(id);

            ctx.status(200).json(new UserResponseBody(deletedUser));
        } catch (ConstraintViolationException exception) {
            ctx.status(400).result(exception.getMessage());
        }
    }
}
