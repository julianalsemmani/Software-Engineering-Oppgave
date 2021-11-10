package web.controller;

import core.model.User;
import core.repository.Repository;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.PostUserBody;
import web.dtos.PutUserBody;
import web.dtos.UserResponseBody;

import javax.validation.ConstraintViolationException;

public class UserController {
    public final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    private void exceptionHandler(Context ctx, Runnable requestHandler) {
        try {
            requestHandler.run();
        } catch (ConstraintViolationException exception) {
            ctx.status(400).result(exception.getMessage());
        } catch (HttpResponseException e) {
            ctx.status(e.getStatus()).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Internal server error");
            e.printStackTrace();
        }
    }

    public void onGetUser(Context ctx) {
        exceptionHandler(ctx, ()->{
            int id = ctx.pathParam("user-id", Integer.class).get();

            User user = repository.getUserById(id);

            if(user == null)
                throw new NotFoundResponse();

            ctx.json(new UserResponseBody(user));
        });
    }

    public void onPostUser(Context ctx) {
        exceptionHandler(ctx, ()->{
            PostUserBody body = JavalinJson.fromJson(ctx.body(), PostUserBody.class);
            User newUser = repository.createUser(body.username, body.password, body.firstName, body.lastName, body.address, body.email);

            ctx.status(201).json(new UserResponseBody(newUser));
        });
    }

    public void onPutUser(Context ctx) {
        exceptionHandler(ctx, ()->{
            int id = ctx.pathParam("user-id", Integer.class).get();
            PutUserBody body = JavalinJson.fromJson(ctx.body(), PutUserBody.class);

            User updatedUser = repository.updateUser(id, body.username, body.password, body.firstName,
                    body.lastName, body.address, body.email);

            if(updatedUser == null)
                throw new NotFoundResponse();

            ctx.status(200).json(new UserResponseBody(updatedUser));
        });
    }

    public void onDeleteUser(Context ctx) {
        exceptionHandler(ctx, ()->{
            int id = ctx.pathParam("user-id", Integer.class).get();

            User deletedUser = repository.deleteUser(id);

            ctx.status(200).json(new UserResponseBody(deletedUser));
        });
    }
}
