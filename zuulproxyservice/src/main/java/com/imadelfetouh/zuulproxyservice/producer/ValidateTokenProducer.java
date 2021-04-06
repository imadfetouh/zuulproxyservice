package com.imadelfetouh.zuulproxyservice.producer;

import com.imadelfetouh.zuulproxyservice.consumer.DefaultRabbitRPCConsumer;
import com.imadelfetouh.zuulproxyservice.rabbit.Producer;
import com.imadelfetouh.zuulproxyservice.rabbit.RabbitConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidateTokenProducer implements Producer<String> {

    private static final Logger logger = Logger.getLogger(ValidateTokenProducer.class.getName());

    private final RabbitConfig rabbitConfig;
    private final String corrId;
    private final String REQUEST_QUEUE_NAME;
    private final String jwtToken;

    public ValidateTokenProducer(String jwtToken) {
        rabbitConfig = RabbitConfig.getInstance();
        corrId = UUID.randomUUID().toString();
        REQUEST_QUEUE_NAME = "validatetoken_queue";
        this.jwtToken = jwtToken;
    }

    @Override
    public String produce(Channel channel) {
        try{
            String replyQueueName = channel.queueDeclare().getQueue();
            AMQP.BasicProperties properties = rabbitConfig.getProperties(corrId, replyQueueName);

            BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
            Consumer consumer = new DefaultRabbitRPCConsumer(channel, blockingQueue, corrId);

            channel.basicConsume(replyQueueName, true, consumer);
            channel.basicPublish("", REQUEST_QUEUE_NAME, properties, jwtToken.getBytes());

            return blockingQueue.poll(3000, TimeUnit.MILLISECONDS);
        }
        catch (IOException | InterruptedException e) {
            logger.log(Level.ALL, e.getMessage());
            Thread.currentThread().interrupt();
        }

        return null;
    }
}
