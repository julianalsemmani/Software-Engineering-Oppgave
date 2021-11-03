package web.controller;

import core.model.StoreUser;
import web.dtos.PostStoreUser;
import core.repository.StartUpRepository;
import io.javalin.core.validation.BodyValidator;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import io.javalin.plugin.json.JavalinJson;

import javax.validation.ConstraintViolationException;

public class UserController {
    public final StartUpRepository startUpRepository;

    public UserController(StartUpRepository startUpRepository) {
        this.startUpRepository = startUpRepository;
    }

    public void onPostStoreUser(Context ctx) {
        try {
            PostStoreUser body = JavalinJson.fromJson(ctx.body(), PostStoreUser.class);

            StoreUser storeUser = new StoreUser(body.firstName, body.lastName, body.address, body.email, body.userName, body.password);
            startUpRepository.createStoreUser(0, storeUser);

            ctx.status(201).result(JavalinJson.toJson(new PostStoreUser(storeUser)));
            //TODO(edward): Maybe get created entity from repository and then serialize that to JSON instead of just
            // serializing the DTO. This may not be necessary, but there could be a case
            // where the entity created in the repository differs from the DTO...
        } catch (ConstraintViolationException exception) {
            ctx.status(400).result(exception.getMessage());
        }
    }
}
