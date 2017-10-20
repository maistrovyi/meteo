package com.meteo.configs;

import com.meteo.listeners.DefaultMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Slf4j
@Profile(value = "dev")
@SpringBootConfiguration
public class RabbitMQConfiguration {

    private final static String SIMPLE_MESSAGE_QUEUE = "simple.queue.name";

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public Queue queue() {
        return new Queue(SIMPLE_MESSAGE_QUEUE);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setRoutingKey(SIMPLE_MESSAGE_QUEUE);
        return template;
    }

    @Bean
    public MessageListener messageListener() {
        return new DefaultMessageListener();
    }

    @Bean
    public SimpleMessageListenerContainer container(Queue queue, MessageListener messageListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queue);
        container.setQueueNames(SIMPLE_MESSAGE_QUEUE);
        container.setMessageListener(messageListener);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return container;
    }

}