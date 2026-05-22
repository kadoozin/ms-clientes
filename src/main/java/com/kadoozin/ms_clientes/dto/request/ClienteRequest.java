package com.kadoozin.ms_clientes.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest(
       @CPF String cpf,
       @NotBlank String nome,
       @NotNull Integer idade
) {
}
