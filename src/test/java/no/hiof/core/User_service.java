package no.hiof.core;

import no.hiof.core.fakes.FakeRepository;
import no.hiof.core.model.User;
import no.hiof.core.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class User_service {
    public Service service;
    public FakeRepository repository;

    public User user1, user2;

    @BeforeEach
    public void setUp() {
        repository = new FakeRepository();
        service = new Service(repository);

        user1 = service.createUser("user1", "password", "number", "one", "user1 address", "user1@fake.com");
        user2 = service.createUser("user2", "password", "number", "two", "user2 address", "user2@fake.com");
    }

    @Test
    public void users_can_register_a_new_user() {
        User newUser = service.createUser("newuser", "newpassword", "newfirstname", "newlastname", "newaddress", "newemail@fake.com");

        assertEquals("newuser", newUser.username);
        assertEquals("newpassword", newUser.password);
        assertEquals("newfirstname", newUser.firstName);
        assertEquals("newlastname", newUser.lastName);
        assertEquals("newaddress", newUser.address);
        assertEquals("newemail@fake.com", newUser.email);
    }

    @Test
    public void users_can_delete_themselves() {
        assertNotNull(repository.getUserById(user1.id));

        service.deleteUser(user1.id);

        assertNull(repository.getUserById(user1.id));
    }

    @Test
    public void users_can_change_their_own_info() {
        service.updateUser(user1.id, "updateduser", "updatedpassword", "updatedfirstname", "updatedlastname", "updatedaddress", "updatedemail@fake.com");

        assertEquals("updateduser", user1.username);
        assertEquals("updatedpassword", user1.password);
        assertEquals("updatedfirstname", user1.firstName);
        assertEquals("updatedlastname", user1.lastName);
        assertEquals("updatedaddress", user1.address);
        assertEquals("updatedemail@fake.com", user1.email);
    }

}
