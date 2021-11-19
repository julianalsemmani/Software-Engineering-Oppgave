package web.controller;

import core.model.StartUp;
import core.service.Service;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.startUp.PutStartUpBody;
import web.dtos.startUp.StartUpResponseBody;

import java.util.UUID;

public class StartUpController {
    public final Service service;

    public StartUpController(Service service) {
        this.service = service;
    }

    public void onGetStartUp(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            StartUp startup = service.repository.getStartUp();
            ctx.json(new StartUpResponseBody(startup));
        });
    }

    public void onPutStartUp(Context ctx) {
        ControllerUtils.exceptionHandler(ctx, () -> {
            UUID id = ctx.pathParam("startup-id", UUID.class).get();
            PutStartUpBody body = JavalinJson.fromJson(ctx.body(), PutStartUpBody.class);

            StartUp updatedStartUp = service.repository.updateStartUp(id, body.startUpName, body.phoneNumber, body.address);

            if (updatedStartUp == null)
                throw new NotFoundResponse();

            ctx.status(200).json(new StartUpResponseBody(updatedStartUp));
        });
    }
}
