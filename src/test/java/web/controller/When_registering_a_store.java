package web.controller;

import web.dtos.PostStoreUser;
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
        PostStoreUser postStoreUser = new PostStoreUser();
        postStoreUser.address = "address to somewhere";
        postStoreUser.userName = "username";
        postStoreUser.firstName = "firstname";
        postStoreUser.lastName = "lastname";
        postStoreUser.email = "email@email.com";
        postStoreUser.password = "fakepassword";

        HttpResponse<String> response = Unirest.post("http://localhost:1234/api/store-user")
                .header("content-type", "application/json")
                .body(JavalinJson.toJson(postStoreUser))
                .asString();

        assertEquals(JavalinJson.toJson(postStoreUser), response.getBody());
        assertEquals(201, response.getStatus());

        System.out.println("Created a store user with the following json structure:");
        System.out.println(response.getBody());
    }

    @Test
    public void creating_a_store_user_but_missing_username() {
        PostStoreUser postStoreUser = new PostStoreUser();
        postStoreUser.address = "address to somewhere";
        // missing username
        postStoreUser.firstName = "firstname";
        postStoreUser.lastName = "lastname";
        postStoreUser.email = "email@email.com";
        postStoreUser.password = "fakepassword";

        HttpResponse<String> response = Unirest.post("http://localhost:1234/api/store-user")
                .header("content-type", "application/json")
                .body(JavalinJson.toJson(postStoreUser))
                .asString();

        System.out.println("Status: " + response.getStatus());
        System.out.println("Body: " + response.getBody());

        // Should return 400 (bad request) since username is missing
        assertEquals(400, response.getStatus());
    }
}
