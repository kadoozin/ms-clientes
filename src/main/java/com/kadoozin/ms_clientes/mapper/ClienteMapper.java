package com.kadoozin.ms_clientes.mapper;

import com.kadoozin.ms_clientes.database.model.Cliente;
import com.kadoozin.ms_clientes.dto.request.ClienteRequest;
import com.kadoozin.ms_clientes.dto.response.ClienteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(source = "id", target = "clienteId")
    ClienteResponse toResponse (Cliente cliente);
    Cliente toEntity(ClienteRequest request);
}
