package com.imadelfetouh.zuulproxyservice;

import com.imadelfetouh.zuulproxyservice.rabbit.RabbitConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RabbitConfigurationTest {

    @Test
    public void testChannel() {
        RabbitConfiguration rabbitConfiguration = RabbitConfiguration.getInstance();

        Assertions.assertNotNull(rabbitConfiguration.getChannel());
    }
}
