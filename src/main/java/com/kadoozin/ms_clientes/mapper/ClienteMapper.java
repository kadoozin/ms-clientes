package com.kadoozin.ms_clientes.mapper;

import com.kadoozin.ms_clientes.database.model.Cliente;
import com.kadoozin.ms_clientes.dto.request.ClienteRequest;
import com.kadoozin.ms_clientes.dto.response.ClienteListResponse;
import com.kadoozin.ms_clientes.dto.response.ClienteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "id", target = "clienteId")
    ClienteResponse toResponse (Cliente cliente);

    @Mapping(target = "id", ignore = true)
    Cliente toEntity(ClienteRequest request);

    ClienteListResponse toListResponse(Cliente cliente);

    List<ClienteListResponse> toListResponse(List<Cliente> clientes);
}
