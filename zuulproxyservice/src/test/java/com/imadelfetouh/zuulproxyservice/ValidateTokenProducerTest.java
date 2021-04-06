package com.imadelfetouh.zuulproxyservice;

import com.imadelfetouh.zuulproxyservice.producer.CreateTokenProducer;
import com.imadelfetouh.zuulproxyservice.producer.ValidateTokenProducer;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidateTokenProducerTest {

    @Test
    public void testValidateTokenCorrect() {
        //create token first
        RabbitProducer<String> rabbitProducer = new RabbitProducer<>();
        CreateTokenProducer createTokenProducer = new CreateTokenProducer("1");

        String token = rabbitProducer.produce(createTokenProducer);

        Assertions.assertNotNull(token);

        RabbitProducer<String> validateProducer = new RabbitProducer<>();
        ValidateTokenProducer validateTokenProducer = new ValidateTokenProducer(token);

        String tokenValidated = validateProducer.produce(validateTokenProducer);

        Assertions.assertNotNull(tokenValidated);
    }
}
