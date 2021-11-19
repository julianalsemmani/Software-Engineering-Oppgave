package web.controller;

import core.model.StartUp;
import core.service.Service;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import web.dtos.startUp.PutStartUpBody;
import web.dtos.startUp.StartUpResponseBody;

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
            PutStartUpBody body = JavalinJson.fromJson(ctx.body(), PutStartUpBody.class);

            StartUp updatedStartUp = service.updateStartUp(body.startUpName, body.address, body.phoneNumber);

            ctx.status(200).json(new StartUpResponseBody(updatedStartUp));
        });
    }
}
