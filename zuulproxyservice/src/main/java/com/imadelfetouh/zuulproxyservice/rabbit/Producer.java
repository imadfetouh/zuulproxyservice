package com.imadelfetouh.zuulproxyservice.rabbit;

import com.rabbitmq.client.Channel;

public interface Producer<T> {

    T produce(Channel channel);

}
