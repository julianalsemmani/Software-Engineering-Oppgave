package web.controller;

import core.model.StartUp;
import core.repository.Repository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.startUp.PostStartUpBody;
import web.dtos.startUp.PutStartUpBody;
import web.dtos.startUp.StartUpResponseBody;

import java.util.UUID;

public class StartUpController {
    public final Repository repository;

    public StartUpController(Repository repository) {
        this.repository = repository;
    }


    public void onGetStartUp(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("startup-id", UUID.class).get();

            StartUp startup = repository.getStartUpById(id);

            if (startup == null)
                throw new NotFoundResponse();

            ctx.json(new StartUpResponseBody(startup));
        });
    }

    public void onPostStartUp(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            PostStartUpBody body = JavalinJson.fromJson(ctx.body(), PostStartUpBody.class);
            StartUp newStartUp = repository.createStartUp(body.startUpName, body.phoneNumber, body.address);

            ctx.status(201).json(new StartUpResponseBody(newStartUp));
        });
    }

    public void onPutStartUp(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("startup-id", UUID.class).get();
            PutStartUpBody body = JavalinJson.fromJson(ctx.body(), PutStartUpBody.class);

            StartUp updatedStartUp = repository.updateStartUp(id, body.startUpName, body.phoneNumber, body.address);

            if (updatedStartUp == null)
                throw new NotFoundResponse();

            ctx.status(200).json(new StartUpResponseBody(updatedStartUp));
        });
    }

    public void onDeleteStartUp(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("startup-id", UUID.class).get();

            StartUp deletedStartUp = repository.deleteStartUp(id);

            ctx.status(200).json(new StartUpResponseBody(deletedStartUp));
        });
    }
}
