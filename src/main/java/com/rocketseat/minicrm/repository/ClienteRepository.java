package com.rocketseat.minicrm.repository;

import com.rocketseat.minicrm.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
