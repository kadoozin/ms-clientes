package com.kadoozin.ms_clientes.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange clienteExchange(@Value("${rabbitmq.exchange.cliente}") String exchange) {
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }

    @Bean
    public Queue clienteCriadoQueue(@Value("${rabbitmq.queue.cliente-criado}") String queue) {
        return QueueBuilder.durable(queue).build();
    }

    @Bean
    public Binding clienteCriadoBinding(Queue clienteCriadoQueue, DirectExchange clienteExchange,
                                        @Value("${rabbitmq.routing-key.cliente-criado}") String routingKey) {
        return BindingBuilder.bind(clienteCriadoQueue).to(clienteExchange).with(routingKey);
    }
}
