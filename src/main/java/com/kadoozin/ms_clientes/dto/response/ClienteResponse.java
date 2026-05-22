package com.kadoozin.ms_clientes.dto.response;

public record ClienteResponse(
        Integer clienteId,
        String cpf,
        String nome,
        Integer idade
) {
}
