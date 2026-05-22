package com.kadoozin.ms_clientes.controller;

import com.kadoozin.ms_clientes.dto.request.ClienteRequest;
import com.kadoozin.ms_clientes.dto.response.ClienteResponse;
import com.kadoozin.ms_clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> save(@RequestBody @Valid ClienteRequest request){
        log.info("Recebida solicitacao de criacao de cliente para cpf={}", maskCpf(request.cpf()));
        ClienteResponse response = clienteService.save(request);
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cpf}")
                .buildAndExpand(response.cpf())
                .toUri();
        log.info("Cliente criado com sucesso para cpf={}", maskCpf(response.cpf()));
        return ResponseEntity.created(headerLocation).body(response);
    }

    @GetMapping({"", "/{cpf}"})
    public ResponseEntity<ClienteResponse> findClienteByCpf(@PathVariable(required = false) @CPF String cpf){
        if (cpf == null || cpf.isBlank()) {
            log.warn("Solicitacao GET /clientes sem cpf informado");
            return ResponseEntity.badRequest().build();
        }
        log.info("Recebida solicitacao de consulta de cliente por cpf={}", maskCpf(cpf));
        return ResponseEntity.ok(clienteService.findClienteByCpf(cpf));
    }

    private String maskCpf(String cpf) {
        if (cpf == null || cpf.length() < 4) {
            return "***";
        }
        return "***" + cpf.substring(cpf.length() - 4);
    }
}
