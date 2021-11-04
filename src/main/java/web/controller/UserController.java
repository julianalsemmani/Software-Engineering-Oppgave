package web.controller;

import core.model.User;
import core.repository.StartUpRepository;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.PostUser;
import web.dtos.UserResponse;

import javax.validation.ConstraintViolationException;

public class UserController {
    public final StartUpRepository startUpRepository;

    public UserController(StartUpRepository startUpRepository) {
        this.startUpRepository = startUpRepository;
    }

    public void onPostUser(Context ctx) {
        try {
            PostUser body = JavalinJson.fromJson(ctx.body(), PostUser.class);

            User newUser = new User(body.firstName, body.lastName, body.address, body.email, body.username, body.password);
            startUpRepository.createUser(newUser);

            ctx.status(201).json(new UserResponse(newUser));
        } catch (ConstraintViolationException exception) {
            ctx.status(400).result(exception.getMessage());
        }
    }
}
