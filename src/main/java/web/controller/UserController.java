package web.controller;

import core.dtos.StoreUserDTO;
import core.repository.StartUpRepository;
import io.javalin.core.validation.BodyValidator;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import io.javalin.plugin.json.JavalinJson;

public class UserController {
    public final StartUpRepository startUpRepository;

    public UserController(StartUpRepository startUpRepository) {
        this.startUpRepository = startUpRepository;
    }

    public void handleCreateStoreUser(Context ctx) {
        try {
            BodyValidator<StoreUserDTO> bodyValidator = ctx.bodyValidator(StoreUserDTO.class);
//            bodyValidator.check("username", (storeUserDTO)->storeUserDTO.userName!=null);
            StoreUserDTO dto = bodyValidator.get();
            startUpRepository.createStoreUser(dto);
            ctx.status(201).result(JavalinJson.toJson(dto));
            //TODO(edward): Maybe get created entity from repository and then serialize that to JSON instead of just
            // serializing the DTO. This may not be necessary, but there could be a case
            // where the entity created in the repository differs from the DTO...
        } catch (HttpResponseException exception) {
            ctx.status(exception.getStatus()).result(exception.getMessage());
        }
    }
}
