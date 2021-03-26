package com.imadelfetouh.zuulproxyservice;

import com.imadelfetouh.zuulproxyservice.consumer.DefaultRabbitRPCConsumer;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitConfig;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitConfiguration;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQBasicProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DefaultRabbitRPCConsumerTest {

    @Test
    public void testQueueCorrect() throws IOException {
        RabbitConfiguration rabbitConfiguration = RabbitConfiguration.getInstance();
        Channel channel = rabbitConfiguration.getChannel();
        AMQP.BasicProperties properties = RabbitConfig.getInstance().getProperties("corrId", "replyTo");

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        DefaultRabbitRPCConsumer defaultRabbitRPCConsumer = new DefaultRabbitRPCConsumer(channel, blockingQueue, "corrId");
        defaultRabbitRPCConsumer.handleDelivery(null, null, properties, "testmessage".getBytes(StandardCharsets.UTF_8));

        Assertions.assertTrue(blockingQueue.contains("testmessage"));
    }

    @Test
    public void testQueueInCorrect() throws IOException {
        RabbitConfiguration rabbitConfiguration = RabbitConfiguration.getInstance();
        Channel channel = rabbitConfiguration.getChannel();
        AMQP.BasicProperties properties = RabbitConfig.getInstance().getProperties("corrIdd", "replyTo");

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        DefaultRabbitRPCConsumer defaultRabbitRPCConsumer = new DefaultRabbitRPCConsumer(channel, blockingQueue, "corrId");
        defaultRabbitRPCConsumer.handleDelivery(null, null, properties, "testmessage".getBytes(StandardCharsets.UTF_8));

        Assertions.assertFalse(blockingQueue.contains("testmessage"));
    }
}
