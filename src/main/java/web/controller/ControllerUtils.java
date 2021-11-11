package web.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;

import javax.validation.ConstraintViolationException;

public class ControllerUtils {
    public static void exceptionHandler(Context ctx, Runnable requestHandler) {
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
}
