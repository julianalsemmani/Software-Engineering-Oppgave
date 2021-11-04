package web.controller;

import core.model.User;
import core.repository.StartUpRepository;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.PostUser;
import web.dtos.PutUser;
import web.dtos.UserResponse;

import javax.validation.ConstraintViolationException;

public class UserController {
    public final StartUpRepository startUpRepository;

    public UserController(StartUpRepository startUpRepository) {
        this.startUpRepository = startUpRepository;
    }

    public void onPostUser(Context ctx) {
        try {
            PostUser body = ctx.bodyAsClass(PostUser.class);

            User newUser = startUpRepository.createUser(body.username, body.password, body.firstName, body.lastName, body.address, body.email);

            ctx.status(201).json(new UserResponse(newUser));
        } catch (ConstraintViolationException exception) {
            ctx.status(400).result(exception.getMessage());
        }
    }

    public void onPutUser(Context ctx) {
        try {
            int id = ctx.pathParam("user-id", Integer.class).get();
            PutUser body = ctx.bodyAsClass(PutUser.class);

            User updatedUser = startUpRepository.updateUser(id, body.username, body.password, body.firstName,
                    body.lastName, body.address, body.email);

            ctx.status(200).json(new UserResponse(updatedUser));
        } catch (ConstraintViolationException exception) {
            ctx.status(400).result(exception.getMessage());
        }
    }
}
