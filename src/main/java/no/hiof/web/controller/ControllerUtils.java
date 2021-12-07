package no.hiof.web.controller;

import no.hiof.core.model.User;
import no.hiof.core.repository.Repository;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;

import javax.validation.ConstraintViolationException;
import java.util.UUID;

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

    public static User getLoggedInUser(Context ctx, Repository repository) {
        String userCookie = ctx.cookie("user");
        if(userCookie != null) {
            UUID loggedInId = UUID.fromString(userCookie);
            return repository.getUserById(loggedInId);
        }
        return null;
    }
}
