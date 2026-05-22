package com.kadoozin.ms_clientes.service;

import com.kadoozin.ms_clientes.database.model.Cliente;
import com.kadoozin.ms_clientes.database.repository.ClienteRepository;
import com.kadoozin.ms_clientes.dto.request.ClienteRequest;
import com.kadoozin.ms_clientes.dto.response.ClienteResponse;
import com.kadoozin.ms_clientes.exceptions.ResourceNotFoundException;
import com.kadoozin.ms_clientes.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Transactional
    public ClienteResponse save(ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.toEntity(clienteRequest);
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Transactional(readOnly = true)
    public ClienteResponse findClienteByCpf(String cpf) {
         Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado: " + cpf));
        return clienteMapper.toResponse(cliente);
    }
}
