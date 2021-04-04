package com.imadelfetouh.zuulproxyservice;

import com.imadelfetouh.zuulproxyservice.producer.CreateTokenProducer;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitConfiguration;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitProducer;
import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CreateTokenProducerTest {

    @Test
    public void testCreateTokenCorrect() {
        RabbitProducer<String> rabbitProducer = new RabbitProducer<>();
        CreateTokenProducer createTokenProducer = new CreateTokenProducer("1");

        String token = rabbitProducer.produce(createTokenProducer);

        Assertions.assertNotNull(token);
    }

    @Test
    public void testCreateTokenCorrectWithoutRabbitProducer() throws IOException, TimeoutException {
        Channel channel = RabbitConfiguration.getInstance().getChannel();
        CreateTokenProducer createTokenProducer = new CreateTokenProducer("1");

        String token = createTokenProducer.produce(channel);
        channel.close();

        Assertions.assertNotNull(token);
        Assertions.assertFalse(channel.isOpen());
    }
}
