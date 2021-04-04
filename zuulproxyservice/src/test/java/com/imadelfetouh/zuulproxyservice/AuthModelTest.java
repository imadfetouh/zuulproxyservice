package com.imadelfetouh.zuulproxyservice;

import com.imadelfetouh.zuulproxyservice.model.AuthModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthModelTest {

    @Test
    public void testEmtyConstructor() {
        AuthModel authModel = new AuthModel();

        Assertions.assertNull(authModel.getUserId());
        Assertions.assertNull(authModel.getUsername());
        Assertions.assertNull(authModel.getPhoto());
        Assertions.assertNull(authModel.getRole());
    }

    @Test
    public void testConstructor() {
        AuthModel authModel = new AuthModel("1", "imad", "imad.jpg", "ADMINISTRATOR");

        Assertions.assertEquals("1", authModel.getUserId());
        Assertions.assertEquals("imad", authModel.getUsername());
        Assertions.assertEquals("imad.jpg", authModel.getPhoto());
        Assertions.assertEquals("ADMINISTRATOR", authModel.getRole());
    }

    @Test
    public void testGettersSetters() {
        AuthModel authModel = new AuthModel();
        authModel.setUserId("1");
        authModel.setUsername("imad");
        authModel.setPhoto("imad.jpg");
        authModel.setRole("ADMINISTRATOR");

        Assertions.assertEquals("1", authModel.getUserId());
        Assertions.assertEquals("imad", authModel.getUsername());
        Assertions.assertEquals("imad.jpg", authModel.getPhoto());
        Assertions.assertEquals("ADMINISTRATOR", authModel.getRole());
    }
}
