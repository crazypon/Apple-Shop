package com.projects.apple_crypto.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MQConfiguration {
    static final String queueName = "firstQueue";
    static final String exchangeName = "testExchange";

    @Bean
    public Queue myQueue() { return new Queue(queueName, false); }

    @Bean
    public Exchange myExchange() {
        return new TopicExchange(exchangeName, false, false);
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("first.key").noargs();
    }
}
