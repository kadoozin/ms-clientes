package com.kadoozin.ms_clientes.service;

import com.kadoozin.ms_clientes.dto.event.ClienteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.cliente}")
    private String clienteExchange;

    @Value("${rabbitmq.routing-key.cliente-criado}")
    private String clienteCriadoRoutingKey;

    public void publishClienteCriado(ClienteEvent event) {
        log.info("Publicando evento {} para cliente cpf={}", event.action(), maskCpf(event.cpf()));
        rabbitTemplate.convertAndSend(clienteExchange, clienteCriadoRoutingKey, event);
        log.info("Evento {} publicado com sucesso", event.action());
    }

    private String maskCpf(String cpf) {
        if (cpf == null || cpf.length() < 4) return "***";
        return "***" + cpf.substring(cpf.length() - 4);
    }
}
