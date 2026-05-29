package com.kadoozin.ms_clientes.dto.event;

import java.io.Serializable;

public record ClienteEvent(
        String action,
        String cpf,
        String nome,
        Integer idade
) implements Serializable {
    public static final String ACTION_CLIENTE_CRIADO = "CLIENTE_CRIADO";
}
