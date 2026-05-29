package com.kadoozin.ms_clientes.dto.response;

public record ClienteListResponse(
        String cpf,
        String nome,
        Integer idade
) {
}
