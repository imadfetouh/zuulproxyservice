package com.imadelfetouh.zuulproxyservice;

import com.imadelfetouh.zuulproxyservice.model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDataTest {

    @Test
    public void testEmtyConstructor() {
        UserData userData = new UserData();

        Assertions.assertNull(userData.getUserId());
        Assertions.assertNull(userData.getUsername());
        Assertions.assertNull(userData.getRole());
    }

    @Test
    public void testConstructor() {
        UserData userData = new UserData("1", "imad", "ADMINISTRATOR");

        Assertions.assertEquals("1", userData.getUserId());
        Assertions.assertEquals("imad", userData.getUsername());
        Assertions.assertEquals("ADMINISTRATOR", userData.getRole());
    }

    @Test
    public void testGettersSetters() {
        UserData userData = new UserData();
        userData.setUserId("1");
        userData.setUsername("imad");
        userData.setRole("ADMINISTRATOR");

        Assertions.assertEquals("1", userData.getUserId());
        Assertions.assertEquals("imad", userData.getUsername());
        Assertions.assertEquals("ADMINISTRATOR", userData.getRole());
    }
}
