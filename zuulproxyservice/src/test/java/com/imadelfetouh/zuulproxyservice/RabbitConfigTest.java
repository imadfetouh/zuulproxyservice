package com.imadelfetouh.zuulproxyservice;

import com.imadelfetouh.zuulproxyservice.rabbit.RabbitConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.impl.AMQBasicProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RabbitConfigTest {

    @Test
    public void testConstructor() {
        RabbitConfig rabbitConfig = RabbitConfig.getInstance();

        Assertions.assertEquals(rabbitConfig, RabbitConfig.getInstance());
    }

    @Test
    public void testProperties() {
        AMQBasicProperties properties = RabbitConfig.getInstance().getProperties("corrId", "replyTo");

        Assertions.assertEquals("corrId", properties.getCorrelationId());
        Assertions.assertEquals("replyTo", properties.getReplyTo());
    }
}
