package com.kadoozin.ms_clientes.database.repository;

import com.kadoozin.ms_clientes.database.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByCpf(String cpf);
}
