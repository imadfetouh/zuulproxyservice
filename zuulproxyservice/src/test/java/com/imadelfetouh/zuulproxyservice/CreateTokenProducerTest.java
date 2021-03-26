package com.imadelfetouh.zuulproxyservice;

import com.imadelfetouh.zuulproxyservice.producer.CreateTokenProducer;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateTokenProducerTest {

    @Test
    public void testCreateTokenCorrect() {
        RabbitProducer<String> rabbitProducer = new RabbitProducer<>();
        CreateTokenProducer createTokenProducer = new CreateTokenProducer(1);

        String token = rabbitProducer.produce(createTokenProducer);

        Assertions.assertNotNull(token);
    }
}
