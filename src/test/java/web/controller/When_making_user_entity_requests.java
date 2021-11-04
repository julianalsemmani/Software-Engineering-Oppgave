package web.controller;

import core.fakes.FakeStartUpRepository;
import persist.StartUpJSONRepository;
import web.dtos.PostUser;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import web.dtos.PutUser;
import web.dtos.UserResponse;
import web.fakes.FakeApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class When_making_user_entity_requests {
    private static final FakeApplication fakeApplication = new FakeApplication(new FakeStartUpRepository());

    @BeforeAll
    public static void beforeAll() {
        fakeApplication.start(1234);
    }

    @AfterEach
    public void afterEach() {
        // Clear repository between tests
        //TODO(edward): This is useless if the tests run in parallel and create race conditions anyway
//        fakeApplication.fakeStartUpRepository.dumpFakeData();
    }

    public PostUser setupFakeUserPostBody() {
        PostUser postStoreUser = new PostUser();
        postStoreUser.username = "username";
        postStoreUser.password = "fakepassword";
        postStoreUser.firstName = "firstname";
        postStoreUser.lastName = "lastname";
        postStoreUser.address = "address to somewhere";
        postStoreUser.email = "email@email.com";
        return postStoreUser;
    }

    @Test
    public void creating_a_user() {
        PostUser postUserBody = setupFakeUserPostBody();

        HttpResponse<String> response = Unirest.post("http://localhost:1234/api/users")
                .header("content-type", "application/json")
                .body(JavalinJson.toJson(postUserBody))
                .asString();

        assertEquals(201, response.getStatus());

        System.out.println("Created a store user with the following json structure:");
        System.out.println(response.getBody());
    }

    @Test
    public void creating_a_user_but_missing_username() {
        PostUser postUserBody = setupFakeUserPostBody();
        postUserBody.username = null; // missing username

        HttpResponse<String> response = Unirest.post("http://localhost:1234/api/users")
                .header("content-type", "application/json")
                .body(JavalinJson.toJson(postUserBody))
                .asString();

        System.out.println("Status: " + response.getStatus());
        System.out.println("Body: " + response.getBody());

        // Should return 400 (bad request) since username is missing
        assertEquals(400, response.getStatus());
    }

    @Test
    public void updating_a_user() {
        // First create a user to update
        PostUser postUserBody = setupFakeUserPostBody();

        HttpResponse<String> postResponse = Unirest.post("http://localhost:1234/api/users")
                .header("content-type", "application/json")
                .body(JavalinJson.toJson(postUserBody))
                .asString();

        UserResponse createdUser = JavalinJson.fromJson(postResponse.getBody(), UserResponse.class);

        assertEquals(201, postResponse.getStatus());

        // Update user
        PutUser putUserBody = new PutUser();
        putUserBody.username = "new_username";
        putUserBody.address = createdUser.address;
        putUserBody.password = "new_password";
        putUserBody.email = createdUser.email;
        putUserBody.firstName =  createdUser.firstName;
        putUserBody.lastName = createdUser.lastName;

        System.out.println("http://localhost:1234/api/users/"+createdUser.id);
        HttpResponse<String> putResponse = Unirest.put("http://localhost:1234/api/users/"+createdUser.id)
                .header("content-type", "application/json")
                .body(JavalinJson.toJson(putUserBody))
                .asString();

        assertEquals(200, putResponse.getStatus());

        UserResponse updatedUser = JavalinJson.fromJson(putResponse.getBody(), UserResponse.class);

        assertEquals("new_username", updatedUser.username);
    }
}
