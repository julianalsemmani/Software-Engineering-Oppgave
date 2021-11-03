package web.controller;

import web.dtos.StoreUserDTO;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import web.fakes.FakeApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class When_registering_a_store {
    private static final FakeApplication fakeApplication = new FakeApplication();

    @BeforeAll
    public static void beforeAll() {
        fakeApplication.start(1234);
    }

    @AfterEach
    public void afterEach() {
        // Clear repository between tests
        //TODO(edward): This is useless if the tests run in parallel and create race conditions anyway
        fakeApplication.fakeStartUpRepository.dumpFakeData();
    }

    @Test
    public void creating_a_store_user() {
        StoreUserDTO storeUserDTO = new StoreUserDTO();
        storeUserDTO.address = "address to somewhere";
        storeUserDTO.userName = "username";
        storeUserDTO.firstName = "firstname";
        storeUserDTO.lastName = "lastname";
        storeUserDTO.email = "email@email.com";
        storeUserDTO.password = "fakepassword";

        HttpResponse<String> response = Unirest.post("http://localhost:1234/api/store-user")
                .header("content-type", "application/json")
                .body(JavalinJson.toJson(storeUserDTO))
                .asString();

        assertEquals(JavalinJson.toJson(storeUserDTO), response.getBody());
        assertEquals(201, response.getStatus());

        System.out.println("Created a store user with the following json structure:");
        System.out.println(response.getBody());
    }

    @Test
    public void creating_a_store_user_but_missing_username() {
        StoreUserDTO storeUserDTO = new StoreUserDTO();
        storeUserDTO.address = "address to somewhere";
        // missing username
        storeUserDTO.firstName = "firstname";
        storeUserDTO.lastName = "lastname";
        storeUserDTO.email = "email@email.com";
        storeUserDTO.password = "fakepassword";

        HttpResponse<String> response = Unirest.post("http://localhost:1234/api/store-user")
                .header("content-type", "application/json")
                .body("{\"firstName\":\"firstname\",\"lastName\":\"lastname\",\"address\":\"address to somewhere\",\"email\":\"email@email.com\",\"password\":\"fakepassword\"}")
                .asString();
        System.out.println(response.getBody());
        // Should return 400 (bad request) since username is missing
        assertEquals(400, response.getStatus());
    }
}
