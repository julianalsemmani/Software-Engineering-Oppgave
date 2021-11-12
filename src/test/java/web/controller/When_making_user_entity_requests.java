package web.controller;

import core.model.User;
import core.repository.fakes.FakeRepository;
import org.junit.jupiter.api.BeforeEach;
import web.dtos.PostUserBody;
import io.javalin.plugin.json.JavalinJson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import web.dtos.PutUserBody;
import web.dtos.UserResponseBody;
import web.WebServer;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class When_making_user_entity_requests {
    private static final FakeRepository FAKE_START_UP_REPOSITORY = new FakeRepository();
    private static final WebServer WEB_SERVER = new WebServer(FAKE_START_UP_REPOSITORY);
    private UUID testUserId;

    @BeforeAll
    public static void beforeAll() {
        WEB_SERVER.start(1234);
    }

    @BeforeEach
    public void setUp() {
        // Clear repository between tests
        FAKE_START_UP_REPOSITORY.dumpFakeData();
        User testUser = FAKE_START_UP_REPOSITORY.createUser("test_user", "test_password", "test_first_name", "test_last_name", "test_address", "test_email");
        testUserId = testUser.id;
    }

    public PostUserBody setupFakeUserPostBody() {
        PostUserBody postStoreUser = new PostUserBody();
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
        PostUserBody postUserBody = setupFakeUserPostBody();

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
        PostUserBody postUserBody = setupFakeUserPostBody();
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
        // Update user
        PutUserBody putUserBody = new PutUserBody();
        putUserBody.username = "new_username";

        HttpResponse<String> putResponse = Unirest.put("http://localhost:1234/api/users/"+testUserId)
                .header("content-type", "application/json")
                .body(JavalinJson.toJson(putUserBody))
                .asString();

        assertEquals(200, putResponse.getStatus());

        UserResponseBody updatedUser = JavalinJson.fromJson(putResponse.getBody(), UserResponseBody.class);

        assertEquals("new_username", updatedUser.username);
        assertNotNull(updatedUser.address); // Make sure that address was not reset even though it was not included in update
    }

    @Test
    public void deleting_a_user() {
        HttpResponse<String> putResponse = Unirest.delete("http://localhost:1234/api/users/"+testUserId)
                .asString();

        assertEquals(200, putResponse.getStatus());

        UserResponseBody deletedUser = JavalinJson.fromJson(putResponse.getBody(), UserResponseBody.class);

        assertNull(FAKE_START_UP_REPOSITORY.getUserById(testUserId));
    }
}
